package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeat;
    private int seatNumber;
    private int rowNumber;
    @ManyToOne
    @JoinColumn(name = "idRoom")
    @JsonBackReference("seat-screeningRoom")
    private ScreeningRoom screeningRoom;
    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    @JsonManagedReference("seat-screeningSeats")
    private List<ScreeningSeats> screeningSeats = new ArrayList<>();

    public Seat(int seatNumber, int rowNumber, ScreeningRoom screeningRoom) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.screeningRoom = screeningRoom;
    }

    public Seat() {
    }

    public Long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Long idSeat) {
        this.idSeat = idSeat;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public List<ScreeningSeats> getScreeningSeats() {
        return screeningSeats;
    }

    public void setScreeningSeats(List<ScreeningSeats> screeningSeats) {
        this.screeningSeats = screeningSeats;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "idSeat=" + idSeat +
                ", seatNumber=" + seatNumber +
                ", rowNumber=" + rowNumber +
                ", screeningRoomNumber=" + screeningRoom.getNumber() +
                '}';
    }
}
