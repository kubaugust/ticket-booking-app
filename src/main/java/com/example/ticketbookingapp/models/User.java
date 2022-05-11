package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotNull(message = "Email cannot be null.")
    @Email(message = "You must provide a correct email address, e.g. 'ex@example.com'.")
    private String email;
    @NotNull(message = "Name cannot be null.")
    @Size(min = 3, message = "Name must be at least 3 characters length.")
    @Pattern(regexp = "\\p{Lu}\\p{Ll}{2,}", message = "Name must start with a capital letter and consist of lowercase letters further only.")
    private String name;
    @NotNull(message = "Surname cannot be null.")
    @Size(min = 3, message = "Surname must be at least 3 characters length.")
    @Pattern(regexp = "\\p{Lu}\\p{Ll}{2,}-\\p{Lu}\\p{Ll}{2,}|\\p{Lu}\\p{Ll}{2,}", message = "Surname must start with a capital letter and consist of at least 2 lowercase letters further only or be separated by a single dash with a second surname.")
    private String surname;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-reservations")
    private List<Reservation> reservations = new ArrayList<>();

    public User(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
    public User() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
