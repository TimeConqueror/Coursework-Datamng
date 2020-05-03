package ru.timeconqueror.coursework.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.timeconqueror.coursework.model.Cinema;

import java.util.UUID;

@Repository
public interface CinemaRepo extends CrudRepository<Cinema, UUID> {

}
