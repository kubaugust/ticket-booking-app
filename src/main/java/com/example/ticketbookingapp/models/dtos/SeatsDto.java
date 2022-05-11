package com.example.ticketbookingapp.models.dtos;

import javax.validation.constraints.NotNull;

public class SeatsDto {
    @NotNull(message = "Seat number cannot be null.")
    private int seatNumber;
    @NotNull(message = "Row number cannot be null.")
    private int rowNumber;

    public SeatsDto(int seatNumber, int rowNumber) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
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
}
