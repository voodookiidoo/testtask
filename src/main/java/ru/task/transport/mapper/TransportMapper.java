package ru.task.transport.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.task.transport.dto.CreationRequestDTO;
import ru.task.transport.dto.IndexResponseDTO;
import ru.task.transport.dto.UpdateRequestDTO;
import ru.task.transport.entity.TransportEntity;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TransportMapper {

    TransportMapper INSTANCE = Mappers.getMapper(TransportMapper.class);

    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget TransportEntity entity, UpdateRequestDTO dto);

    @Mapping(target = "id")
    UpdateRequestDTO entityToUpdateReq(TransportEntity entity);

    @Mapping(target = "id", ignore = true)
    TransportEntity creationReqToEntity(CreationRequestDTO dto);

    IndexResponseDTO indexMap(TransportEntity entity);

}
