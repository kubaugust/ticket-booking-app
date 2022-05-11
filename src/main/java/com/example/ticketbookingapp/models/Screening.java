package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScreening;
    @FutureOrPresent(message = "Start time cannot be in the past")
    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;
    @ManyToOne
    @JoinColumn(name = "idMovie")
    @JsonBackReference("movie-screening")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "idScreeningRoom")
    @JsonBackReference("screening-screeningRoom")
    private ScreeningRoom screeningRoom;
    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    @JsonManagedReference("screening-screeningSeats")
    private List<ScreeningSeats> screeningSeats = new ArrayList<>();
    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    @JsonManagedReference("screening-reservations")
    private List<Reservation> reservations = new ArrayList<>();

    public Screening(LocalDateTime startTime, Movie movie, ScreeningRoom screeningRoom) {
        this.startTime = startTime;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
    }

    public Screening() {
    }

    public Long getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(Long idScreening) {
        this.idScreening = idScreening;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "idScreening=" + idScreening +
                ", startTime=" + startTime +
                ", movieTitle=" + movie.getTitle() +
                ", screeningRoomNumber=" + screeningRoom.getNumber() +
                '}';
    }
}
