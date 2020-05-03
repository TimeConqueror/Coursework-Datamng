package ru.timeconqueror.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.timeconqueror.coursework.model.Film;
import ru.timeconqueror.coursework.model.Ticket;
import ru.timeconqueror.coursework.repo.TicketRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService implements SimpleService<Ticket> {

    private TicketRepo repo;

    @Autowired
    public void setRepo(TicketRepo repo) {
        this.repo = repo;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return repo.save(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        repo.delete(ticket);
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<Ticket> findAll() {
        return repo.findAll();
    }
}
