package ru.timeconqueror.coursework.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Embeddable
@Table(name = "Sessions")
public class Session {
    @Id
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid", unique = true, updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "date", nullable = false)
    private Date format;

    @Column(name = "film_id", nullable = false)
    private UUID filmID;

    @Column(name = "hall_id", nullable = false)
    private UUID hallID;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "time", nullable = false)
    private Time time;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFormat() {
        return format;
    }

    public void setFormat(Date format) {
        this.format = format;
    }

    public UUID getFilmID() {
        return filmID;
    }

    public void setFilmID(UUID filmID) {
        this.filmID = filmID;
    }

    public UUID getHallID() {
        return hallID;
    }

    public void setHallID(UUID hallID) {
        this.hallID = hallID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", format=" + format +
                ", filmID=" + filmID +
                ", hallID=" + hallID +
                ", price=" + price +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return id.equals(session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
