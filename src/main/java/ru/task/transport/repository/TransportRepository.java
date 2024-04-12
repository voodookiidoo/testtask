package ru.task.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.transport.entity.TransportEntity;

@Repository
public interface TransportRepository extends JpaRepository<TransportEntity, Integer> {

    @Override
    <S extends TransportEntity> S save(S entity);


}
