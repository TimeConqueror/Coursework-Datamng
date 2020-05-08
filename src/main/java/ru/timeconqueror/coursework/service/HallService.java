package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timeconqueror.coursework.model.Hall;
import ru.timeconqueror.coursework.repo.HallRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class HallService implements SimpleService<Hall> {
    private HallRepo repo;
    private SessionService sessionService;

    @Autowired
    public void setRepo(HallRepo repo) {
        this.repo = repo;
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public Hall save(Hall hall) {
        return repo.save(hall);
    }

    @Override
    public void delete(Hall hall) {
        sessionService.deleteAllByHallId(hall.getId());
        repo.delete(hall);
    }

    @Override
    public Optional<Hall> findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<Hall> findAll() {
        return repo.findAll();
    }

    public Iterable<Hall> findAllByCinemaId(UUID cinemaId) {
        return repo.findAllByCinemaId(cinemaId);
    }

    @Transactional
    public void deleteAllByCinemaId(UUID cinemaID) {
        findAllByCinemaId(cinemaID).forEach(this::delete);
    }
}
