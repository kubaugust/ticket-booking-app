package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.dtos.requests.ScreeningRequestDto;
import com.example.ticketbookingapp.models.dtos.responses.ScreeningInfoResponseDto;
import com.example.ticketbookingapp.models.dtos.responses.ScreeningsResponseDto;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;

public interface IScreeningService {
    List<ScreeningsResponseDto> getAllScreenings(LocalDateTime startTime, LocalDateTime endTime);
    ScreeningInfoResponseDto getScreening(Long id);
    Screening addScreening(ScreeningRequestDto newScreening, BindingResult result);
}
