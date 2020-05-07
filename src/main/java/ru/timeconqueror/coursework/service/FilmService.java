package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.timeconqueror.coursework.model.Film;
import ru.timeconqueror.coursework.repo.FilmRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class FilmService implements SimpleService<Film> {
    private FilmRepo repo;
    private SessionService sessionService;

    @Autowired
    public void setRepo(FilmRepo repo) {
        this.repo = repo;
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public Film save(Film film) {
        return repo.save(film);
    }

    @Override
    public void delete(Film film) {
        sessionService.deleteAllByFilmId(film.getId());
        repo.delete(film);
    }

    @Override
    public Optional<Film> findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<Film> findAll() {
        return repo.findAll();
    }
}
