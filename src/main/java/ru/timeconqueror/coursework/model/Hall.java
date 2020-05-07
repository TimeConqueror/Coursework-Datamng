package ru.timeconqueror.coursework.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Embeddable
@Table(name = "Halls")
public class Hall {
    @Id
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid", unique = true, updatable = false)
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "format", nullable = false)
    private String format;

    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "row_number", nullable = false)
    private int rowNumber;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "place_number", nullable = false)
    private int placeNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", format='" + format + '\'' +
                ", name='" + name + '\'' +
                ", rowNumber=" + rowNumber +
                ", placeNumber='" + placeNumber + '\'' +
                ", cinemaID=" + cinema.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;
        Hall hall = (Hall) o;
        return id.equals(hall.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
