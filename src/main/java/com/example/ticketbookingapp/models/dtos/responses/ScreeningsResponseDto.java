package com.example.ticketbookingapp.models.dtos.responses;

import java.time.LocalDateTime;

public class ScreeningsResponseDto {
    private Long id;
    private String title;
    private LocalDateTime startTime;

    public ScreeningsResponseDto(Long id, String title, LocalDateTime startTime) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
