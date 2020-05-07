package ru.timeconqueror.coursework.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.timeconqueror.coursework.model.Session;

import java.util.UUID;

@Repository
public interface SessionRepo extends CrudRepository<Session, UUID> {
    void deleteAllByHallId(UUID hallId);

    void deleteAllByFilmId(UUID filmId);
}
