package ru.task.transport.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.data.DefaultRepositoryTagsProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    private final DefaultRepositoryTagsProvider repositoryTagsProvider;

    TransportService transportService;

    @PostMapping("/new")
    public String createHandler(@ModelAttribute CreationRequestDTO transport,
                                @ModelAttribute("chain") IndexFilteredRequestDTO chain) {

        TransportEntity entity = TransportMapper.INSTANCE.mapTo(transport);
        transportService.saveEntity(entity);
        return "redirect:/transport/index";
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHandler(@PathVariable Integer id) {
        boolean deleted = transportService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public String searchHandler(@PathVariable Integer id, @ModelAttribute UpdateRequestDTO dto, BindingResult result, Model model) {
        Optional<TransportEntity> item = transportService.findById(id);
        if (item.isEmpty()) {
            return "redirect:/transport/index";
        }
        model.addAttribute("item", item.get());
        return "editform";
    }

    @GetMapping("/create")
    public String creationFormHandler(@ModelAttribute("transport") CreationRequestDTO transport) {
        return "item";
    }

    @GetMapping("/index")
    public String indexHandler(@ModelAttribute("chain") IndexFilteredRequestDTO chain, BindingResult result, Model model) {
        List<IndexResponseDTO> list;
        if (!result.hasErrors()) {
            list = transportService.indexFiltered(chain);
        } else {
            list = transportService.index();
        }
        model.addAttribute("transport_list", list);
        return "index";
    }

    @PostMapping("/update")
    public String updateHandler(@RequestBody UpdateRequestDTO dto) {
        transportService.updateEntity(dto);
        return "redirect:/transport/index";
    }


}
