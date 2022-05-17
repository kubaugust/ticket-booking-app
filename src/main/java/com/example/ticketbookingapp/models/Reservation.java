package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    @PositiveOrZero(message = "Amount cannot be negative.")
    private BigDecimal amount;
    @Future(message = "Reservation expiration time must be in future.")
    private LocalDateTime expirationTime;
    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonBackReference("user-reservations")
    private User user;
    @ManyToOne
    @JoinColumn(name = "idScreening")
    @JsonBackReference("screening-reservations")
    private Screening screening;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonManagedReference("reservation-reservationTickets")
    private List<ReservationTickets> reservationTickets = new ArrayList<>();

    public Reservation(BigDecimal amount, LocalDateTime expirationTime, User user, Screening screening) {
        this.amount = amount;
        this.expirationTime = expirationTime;
        this.user = user;
        this.screening = screening;
    }

    public Reservation() {
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public List<ReservationTickets> getReservationTickets() {
        return reservationTickets;
    }

    public void setReservationTickets(List<ReservationTickets> reservationTickets) {
        this.reservationTickets = reservationTickets;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", amount=" + amount +
                ", expirationTime=" + expirationTime +
                ", userId=" + user.getIdUser() +
                ", screeningId=" + screening.getIdScreening() +
                '}';
    }
}
