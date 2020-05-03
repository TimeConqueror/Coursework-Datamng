package ru.timeconqueror.coursework.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.timeconqueror.coursework.model.Film;

import java.util.UUID;

@Repository
public interface FilmRepo extends CrudRepository<Film, UUID> {
}
