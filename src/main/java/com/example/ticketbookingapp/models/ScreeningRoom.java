package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ScreeningRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;
    @PositiveOrZero(message = "Screening room number cannot be negative.")
    @Column(nullable = false)
    private int number;
    @PositiveOrZero(message = "Rows number cannot be negative.")
    @Column(nullable = false)
    private int rows;
    @PositiveOrZero(message = "Seats in a row number cannot be negative.")
    @Column(nullable = false)
    private int rowSeats;
    @OneToMany(mappedBy = "screeningRoom", cascade = CascadeType.ALL)
    @JsonManagedReference("seat-screeningRoom")
    private List<Seat> seats = new ArrayList<>();
    @OneToMany(mappedBy = "screeningRoom", cascade = CascadeType.ALL)
    @JsonManagedReference("screening-screeningRoom")
    private List<Screening> screenings = new ArrayList<>();

    public ScreeningRoom(int number, int rows, int rowSeats) {
        this.number = number;
        this.rows = rows;
        this.rowSeats = rowSeats;
    }

    public ScreeningRoom() {
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRowSeats() {
        return rowSeats;
    }

    public void setRowSeats(int rowSeats) {
        this.rowSeats = rowSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public String toString() {
        return "ScreeningRoom{" +
                "idRoom=" + idRoom +
                ", number=" + number +
                ", rows=" + rows +
                ", rowSeats=" + rowSeats +
                '}';
    }
}
