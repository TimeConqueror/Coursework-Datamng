package ru.timeconqueror.coursework.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Embeddable
@Table(name = "Tickets")
public class Ticket {
    @Id
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid", unique = true, updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "row_index", nullable = false)
    private int rowIndex;

    @Column(name = "place_index", nullable = false)
    private int placeIndex;

    @Column(name = "session_id", nullable = false)
    private UUID sessionID;

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getPlaceIndex() {
        return placeIndex;
    }

    public void setPlaceIndex(int placeIndex) {
        this.placeIndex = placeIndex;
    }

    public UUID getSessionID() {
        return sessionID;
    }

    public void setSessionID(UUID sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", rowIndex=" + rowIndex +
                ", placeIndex=" + placeIndex +
                ", sessionID=" + sessionID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return rowIndex == ticket.rowIndex &&
                placeIndex == ticket.placeIndex &&
                id.equals(ticket.id) &&
                sessionID.equals(ticket.sessionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowIndex, placeIndex, sessionID);
    }
}
