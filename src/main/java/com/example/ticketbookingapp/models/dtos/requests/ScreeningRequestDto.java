package com.example.ticketbookingapp.models.dtos.requests;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ScreeningRequestDto {
    @NotNull(message = "Movie title cannot be null.")
    private String movieTitle;
    @NotNull(message = "Start time cannot be null.")
    private LocalDateTime startTime;
    @NotNull(message = "Screening room number cannot be null.")
    private int screeningRoomNumber;

    public ScreeningRequestDto(String movieTitle, LocalDateTime startTime, int screeningRoomNumber) {
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.screeningRoomNumber = screeningRoomNumber;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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
}
