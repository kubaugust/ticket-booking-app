package com.example.ticketbookingapp.models.dtos;

import javax.validation.constraints.NotNull;

public class ReservationTicketDto {
    @NotNull(message = "Ticket type cannot be null.")
    private String ticketType;
    @NotNull(message = "Number of ticket type cannot be null.")
    private int numberOfTicketType;

    public ReservationTicketDto(String ticketType, int numberOfTicketType) {
        this.ticketType = ticketType;
        this.numberOfTicketType = numberOfTicketType;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getNumberOfTicketType() {
        return numberOfTicketType;
    }

    public void setNumberOfTicketType(int numberOfTicketType) {
        this.numberOfTicketType = numberOfTicketType;
    }
}
