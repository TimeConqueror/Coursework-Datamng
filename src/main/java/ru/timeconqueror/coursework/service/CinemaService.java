package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.timeconqueror.coursework.model.Cinema;
import ru.timeconqueror.coursework.repo.CinemaRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaService implements SimpleService<Cinema> {
    private CinemaRepo repo;

    @Autowired
    public void setRepo(CinemaRepo repo) {
        this.repo = repo;
    }

    @Override
    public Cinema save(Cinema cinema) {
        return repo.save(cinema);
    }

    @Override
    public void delete(Cinema cinema) {
        repo.delete(cinema);
    }

    public Optional<Cinema> findById(UUID id){
        return repo.findById(id);
    }

    @Override
    public Iterable<Cinema> findAll() {
        return repo.findAll();
    }
}
