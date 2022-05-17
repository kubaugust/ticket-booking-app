package com.example.ticketbookingapp.models.dtos.responses;

import com.example.ticketbookingapp.models.dtos.SeatsDto;

import java.time.LocalDateTime;
import java.util.List;

public class ScreeningInfoResponseDto {
    private String title;
    private LocalDateTime startTime;
    private int screeningRoomNumber;
    private List<SeatsDto> availableSeats;

    public ScreeningInfoResponseDto(String title, LocalDateTime startTime, int screeningRoomNumber, List<SeatsDto> availableSeats) {
        this.title = title;
        this.startTime = startTime;
        this.screeningRoomNumber = screeningRoomNumber;
        this.availableSeats = availableSeats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getScreeningRoomNumber() {
        return screeningRoomNumber;
    }

    public void setScreeningRoomNumber(int screeningRoomNumber) {
        this.screeningRoomNumber = screeningRoomNumber;
    }

    public List<SeatsDto> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<SeatsDto> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
