package ru.timeconqueror.coursework.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.timeconqueror.coursework.model.Hall;

import java.util.UUID;

@Repository
public interface HallRepo extends CrudRepository<Hall, UUID> {
    void deleteAllByCinemaId(UUID cinemaId);
}
