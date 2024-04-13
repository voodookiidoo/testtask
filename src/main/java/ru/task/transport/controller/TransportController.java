package ru.task.transport.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.task.transport.config.Constants;
import ru.task.transport.dto.CreationRequestDTO;
import ru.task.transport.dto.IndexFilteredRequestDTO;
import ru.task.transport.dto.IndexResponseDTO;
import ru.task.transport.dto.UpdateRequestDTO;
import ru.task.transport.entity.TransportEntity;
import ru.task.transport.mapper.TransportMapper;
import ru.task.transport.service.TransportService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(Constants.TRANSPORT_URL)
@Slf4j
@AllArgsConstructor
public class TransportController {

    private final LocalValidatorFactoryBean validator;

    TransportService transportService;

    public String redirectTo(String to) {
        return "redirect:%s/%s".formatted(Constants.TRANSPORT_URL, to);
    }

    @PostMapping("/new")
    public String createHandler(@ModelAttribute("transport") @Validated CreationRequestDTO transport,
                                BindingResult result,
                                @ModelAttribute("chain") IndexFilteredRequestDTO chain) {
        if (result.hasErrors() || !validator.validate(transport).isEmpty()) {
            return redirectTo("index");
        }
        TransportEntity entity = TransportMapper.INSTANCE.creationReqToEntity(transport);
        transportService.saveEntity(entity);
        return redirectTo("index");
    }


    @GetMapping("/{id}")
    public String searchHandler(@PathVariable Integer id,
                                Model model) {
        Optional<TransportEntity> item = transportService.findById(id);
        if (item.isEmpty()) {
            return redirectTo("index");
        }

        UpdateRequestDTO dto = TransportMapper.INSTANCE.entityToUpdateReq(item.get());
        model.addAttribute("item", dto);
        return "editform";
    }

    @GetMapping("/create")
    public String creationFormHandler(@ModelAttribute("item") CreationRequestDTO dto) {
        return "creationform";
    }

    @GetMapping("/index")
    public String indexHandler(@ModelAttribute("chain") IndexFilteredRequestDTO chain,
                               BindingResult result, Model model) {
        log.info("Index called");
        List<IndexResponseDTO> list;
        if (!result.hasErrors()) {
            list = transportService.indexFiltered(chain);
        } else {
            list = transportService.index();
        }
        model.addAttribute("transport_list", list);
        return "index";
    }

    @PostMapping(value = "/update")
    public String updateHandler(
            @ModelAttribute("item") @Validated UpdateRequestDTO item,
            BindingResult result) {
        if (result.hasErrors() || !validator.validate(item).isEmpty()) {
            log.info("Something bad happened");
            log.info("Errors:%s".formatted(result));
            return redirectTo("index");
        }
        log.info(result.toString());
        log.info(item.toString());
        transportService.updateEntity(item.getId(), item);
        return redirectTo("index");
    }


}
