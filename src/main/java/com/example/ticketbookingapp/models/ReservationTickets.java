package com.example.ticketbookingapp.models;

import com.example.ticketbookingapp.models.keys.ReservationTicketsPK;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@IdClass(ReservationTicketsPK.class)
public class ReservationTickets {
    @Id
    private Long idReservation;
    @Id
    private Long idTicket;
    @Positive(message = "Number of tickets must be a positive number.")
    @Column(nullable = false)
    private int numberOfTicketType;
    @ManyToOne
    @MapsId("idReservation")
    @JoinColumn(name = "idReservation")
    @JsonBackReference("reservation-reservationTickets")
    private Reservation reservation;
    @ManyToOne
    @MapsId("idTicket")
    @JoinColumn(name = "idTicket")
    @JsonBackReference("ticket-reservationTickets")
    private Ticket ticket;

    public ReservationTickets(int numberOfTicketType, Reservation reservation, Ticket ticket) {
        this.idReservation = reservation.getIdReservation();
        this.idTicket = ticket.getIdTicket();
        this.numberOfTicketType = numberOfTicketType;
        this.reservation = reservation;
        this.ticket = ticket;
    }

    public ReservationTickets() {
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public int getNumberOfTicketType() {
        return numberOfTicketType;
    }

    public void setNumberOfTicketType(int numberOfTicketType) {
        this.numberOfTicketType = numberOfTicketType;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "ReservationTickets{" +
                "idReservation=" + idReservation +
                ", idTicket=" + idTicket +
                ", numberOfTicketType=" + numberOfTicketType +
                '}';
    }
}
