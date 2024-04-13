package ru.task.transport.service;

import ru.task.transport.dto.IndexFilteredRequestDTO;
import ru.task.transport.dto.IndexResponseDTO;
import ru.task.transport.dto.UpdateRequestDTO;
import ru.task.transport.entity.TransportEntity;

import java.util.List;
import java.util.Optional;

public interface TransportService {

    List<IndexResponseDTO> index();

    Optional<TransportEntity> findById(int id);

    void saveEntity(TransportEntity entity);

    boolean deleteById(int id);

    List<IndexResponseDTO> indexFiltered(IndexFilteredRequestDTO chain);

    void updateEntity(Integer id, UpdateRequestDTO dto);

}
