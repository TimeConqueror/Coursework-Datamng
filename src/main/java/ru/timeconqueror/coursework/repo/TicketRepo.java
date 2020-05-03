package ru.timeconqueror.coursework.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.timeconqueror.coursework.model.Ticket;

import java.util.UUID;

@Repository
public interface TicketRepo extends CrudRepository<Ticket, UUID> {
}
