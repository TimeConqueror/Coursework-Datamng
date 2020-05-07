package ru.timeconqueror.coursework.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.timeconqueror.coursework.model.Session;

import javax.validation.constraints.Pattern;
import java.util.UUID;

@Repository
public interface SessionRepo extends CrudRepository<Session, UUID> {
    void deleteAllByHallId(UUID hallId);

    void deleteAllByFilmId(UUID filmId);

    Iterable<Session> findAllByPriceGreaterThan(@Pattern(regexp = "^(\\d+|\\d+[,.]\\d{2})$", message = "должно быть целочисленным числом или числом с двумя числами после запятой") String price);

    @Query(value = "SELECT s FROM Session s " +
            "LEFT JOIN Hall h ON s.hall = h.id " +
            "LEFT JOIN Cinema c ON h.cinema = c.id " +
            "WHERE c.name = ?1")
    Iterable<Session> findAllByCinemaName(String name);
}
