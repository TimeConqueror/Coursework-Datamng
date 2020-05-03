package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.timeconqueror.coursework.model.Film;
import ru.timeconqueror.coursework.model.Hall;
import ru.timeconqueror.coursework.repo.HallRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class HallService implements SimpleService<Hall>{
    private HallRepo repo;

    @Autowired
    public void setRepo(HallRepo repo) {
        this.repo = repo;
    }


    @Override
    public Hall save(Hall hall) {
        return repo.save(hall);
    }

    @Override
    public void delete(Hall hall) {
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
}
