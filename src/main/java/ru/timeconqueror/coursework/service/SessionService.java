package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timeconqueror.coursework.model.Session;
import ru.timeconqueror.coursework.repo.SessionRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService implements SimpleService<Session> {
    private SessionRepo repo;

    @Autowired
    public void setRepo(SessionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Session save(Session session) {
        return repo.save(session);
    }

    @Override
    public void delete(Session session) {
        repo.delete(session);
    }

    @Override
    public Optional<Session> findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<Session> findAll() {
        return repo.findAll();
    }

    @Transactional
    public void deleteAllByFilmId(UUID filmID) {
        findAllByFilmId(filmID).forEach(this::delete);
    }

    @Transactional
    public Iterable<Session> findAllByFilmId(UUID filmID) {
        return repo.findAllByFilmId(filmID);
    }

    @Transactional
    public void deleteAllByHallId(UUID hallID) {
        findAllByHallId(hallID).forEach(this::delete);
    }

    @Transactional
    public Iterable<Session> findAllByHallId(UUID hallID) {
        return repo.findAllByHallId(hallID);
    }

    @Transactional
    public Iterable<Session> findAllByCinemaName(String cinemaName) {
        return repo.findAllByCinemaName(cinemaName);
    }

    @Transactional
    public Iterable<Session> findAllByPriceGreaterThan(String price) {
        return repo.findAllByPriceGreaterThan(price);
    }
}
