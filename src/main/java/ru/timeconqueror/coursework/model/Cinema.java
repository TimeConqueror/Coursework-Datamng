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
@Table(name = "cinemas")
public class Cinema {
    @Id
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid", unique = true, updatable = false)
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(min = 3, max = 20, message = MessageConstants.WRONG_SIZE_MSG)
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(message = "Телефон должен быть таким: +#(###)###-##-##", regexp = "\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}")
    @Column(name = "tel", nullable = false)
    private String tel;

    @Size(min = 3, max = 255, message = MessageConstants.WRONG_SIZE_MSG)
    @Column(name = "address", nullable = false)
    private String address;

    @Pattern(message = "Неверный сайт", regexp = "^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(\\/\\S*)?$")
    @Column(name = "website", nullable = false)
    private String website;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema)) return false;
        Cinema cinema = (Cinema) o;
        return id.equals(cinema.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
