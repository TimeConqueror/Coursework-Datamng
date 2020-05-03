package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.timeconqueror.coursework.model.Film;
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
}
