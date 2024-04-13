package ru.task.transport.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.task.transport.dto.IndexFilteredRequestDTO;
import ru.task.transport.dto.IndexResponseDTO;
import ru.task.transport.dto.UpdateRequestDTO;
import ru.task.transport.entity.TransportEntity;
import ru.task.transport.mapper.TransportMapper;
import ru.task.transport.repository.TransportRepository;
import ru.task.transport.utils.UtilFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;


    @Override
    public List<IndexResponseDTO> index() {
        return transportRepository.findAll().stream().map(TransportMapper.INSTANCE::indexMap).toList();
    }

    @Override
    public Optional<TransportEntity> findById(int id) {

        return transportRepository.findById(id);
    }

    @Override
    public void saveEntity(TransportEntity entity) {
        transportRepository.save(entity);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<IndexResponseDTO> indexFiltered(IndexFilteredRequestDTO chain) {
        Stream<IndexResponseDTO> dataStream = transportRepository
                .findAll().stream().map(TransportMapper.INSTANCE::indexMap);
        List<Predicate<IndexResponseDTO>> predicates = new ArrayList<>();
        if (UtilFunctions.nullOrBlankString(chain.getName())) {
            predicates.add(data -> data.getName().startsWith(chain.getName()));
        }
        if (UtilFunctions.nullOrBlankString(chain.getBrand())) {
            predicates.add(data -> data.getBrand().startsWith(chain.getBrand()));
        }
        if (UtilFunctions.nullOrBlankString(chain.getGovNumber())) {
            predicates.add(data -> data.getGovNumber().startsWith(chain.getGovNumber()));
        }
        if (UtilFunctions.nullOrBlankString(chain.getCategory())) {
            predicates.add(data -> data.getCategory().startsWith(chain.getCategory()));
        }
        if (chain.getYear() != null) {
            predicates.add(data -> Objects.equals(data.getYear(), chain.getYear()));
        }
        for (Predicate<IndexResponseDTO> pred : predicates) {
            dataStream = dataStream.filter(pred);
        }
        return dataStream.toList();
    }

    @Override
    public void updateEntity(Integer id, UpdateRequestDTO dto) {
        Optional<TransportEntity> entityOptional = findById(id);
        if (entityOptional.isEmpty()) return;
        TransportEntity entity = entityOptional.get();
        TransportMapper.INSTANCE.merge(entity, dto);
        transportRepository.save(entity);
    }

}
