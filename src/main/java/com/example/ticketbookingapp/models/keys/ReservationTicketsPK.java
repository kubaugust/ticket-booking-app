package com.example.ticketbookingapp.models.keys;

import java.io.Serializable;
import java.util.Objects;

public class ReservationTicketsPK implements Serializable {
    private Long idReservation;
    private Long idTicket;

    public ReservationTicketsPK(Long idReservation, Long idTicket) {
        this.idReservation = idReservation;
        this.idTicket = idTicket;
    }

    public ReservationTicketsPK() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationTicketsPK that = (ReservationTicketsPK) o;
        return Objects.equals(idReservation, that.idReservation) && Objects.equals(idTicket, that.idTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idTicket);
    }
}
