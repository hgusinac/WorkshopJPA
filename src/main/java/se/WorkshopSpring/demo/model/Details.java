package se.WorkshopSpring.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private int detailsId;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;

    public Details() {
    }

    public Details(int detailsId, String name, String email, LocalDate birthDate) {
        this.detailsId = detailsId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


}
