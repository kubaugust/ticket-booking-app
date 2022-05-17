package com.example.ticketbookingapp.models.dtos.requests;

import com.example.ticketbookingapp.models.dtos.ReservationTicketDto;
import com.example.ticketbookingapp.models.dtos.SeatsDto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ReservationRequestDto {
    @NotNull(message = "Id of screening cannot be null.")
    private Long idScreening;
    @NotNull(message = "Id of user cannot be null.")
    private Long idUser;
    @NotNull(message = "Name cannot be null.")
    private String name;
    @NotNull(message = "Surname cannot be null.")
    private String surname;
    @NotNull(message = "Reservation tickets list cannot be null.")
    private List<ReservationTicketDto> reservationTickets;
    @NotNull(message = "Seats list cannot be null.")
    private List<SeatsDto> seats;

    public ReservationRequestDto(Long idScreening, Long idUser, String name, String surname, List<ReservationTicketDto> reservationTickets, List<SeatsDto> seats) {
        this.idScreening = idScreening;
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.reservationTickets = reservationTickets;
        this.seats = seats;
    }

    public Long getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(Long idScreening) {
        this.idScreening = idScreening;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public List<ReservationTicketDto> getReservationTickets() {
        return reservationTickets;
    }

    public void setReservationTickets(List<ReservationTicketDto> reservationTickets) {
        this.reservationTickets = reservationTickets;
    }

    public List<SeatsDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatsDto> seats) {
        this.seats = seats;
    }
}
