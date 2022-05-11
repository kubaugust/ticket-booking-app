package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTicket;
    @NotNull(message = "Type cannot be null.")
    private String type;
    @NotNull(message = "Price cannot be null.")
    private BigDecimal price;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JsonManagedReference("ticket-reservationTickets")
    private List<ReservationTickets> reservationTickets = new ArrayList<>();

    public Ticket(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public Ticket() {
    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ReservationTickets> getReservationTickets() {
        return reservationTickets;
    }

    public void setReservationTickets(List<ReservationTickets> reservationTickets) {
        this.reservationTickets = reservationTickets;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
