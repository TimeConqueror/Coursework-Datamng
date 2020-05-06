package ru.timeconqueror.coursework.model;

import org.hibernate.annotations.Type;
import ru.timeconqueror.coursework.util.MessageConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Embeddable
@Table(name = "Films")
public class Film {
    @Id
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid", unique = true, updatable = false)
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(min = 3, max = 255, message = MessageConstants.WRONG_SIZE_MSG)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(max = 1000, message = MessageConstants.WRONG_SIZE_MSG)
    @Column(name = "`desc`", nullable = false, length = 1000)
    private String desc;

    @NotNull
    @Pattern(regexp = "^\\d{1,2}\\+$", message = "Требуемый формат: #+ или ##+")
    @Column(name = "age_limit", nullable = false)
    private String ageLimit;

    @Lob
    @Column(name = "logo", nullable = false)
    private byte[] logo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return id.equals(film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
