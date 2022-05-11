package com.example.ticketbookingapp.models;

import com.example.ticketbookingapp.models.keys.ScreeningSeatsPK;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@IdClass(ScreeningSeatsPK.class)
public class ScreeningSeats {
    @Id
    private Long idScreening;
    @Id
    private Long idSeat;
    private boolean isOccupied;
    @ManyToOne
    @MapsId("idScreening")
    @JoinColumn(name = "idScreening")
    @JsonBackReference("screening-screeningSeats")
    private Screening screening;
    @ManyToOne
    @MapsId("idSeat")
    @JoinColumn(name = "idSeat")
    @JsonBackReference("seat-screeningSeats")
    private Seat seat;


    public ScreeningSeats(boolean isOccupied, Screening screening, Seat seat) {
        this.idScreening = screening.getIdScreening();
        this.idSeat = seat.getIdSeat();
        this.isOccupied = isOccupied;
        this.screening = screening;
        this.seat = seat;
    }

    public ScreeningSeats() {
    }

    public Long getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(Long idScreening) {
        this.idScreening = idScreening;
    }

    public Long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Long idSeat) {
        this.idSeat = idSeat;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "ScreeningSeats{" +
                "idScreening=" + idScreening +
                ", idSeat=" + idSeat +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
