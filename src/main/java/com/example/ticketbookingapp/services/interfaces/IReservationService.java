package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.dtos.requests.ReservationRequestDto;
import com.example.ticketbookingapp.models.dtos.responses.ReservationResponseDto;
import org.springframework.validation.BindingResult;

public interface IReservationService {
    ReservationResponseDto addReservation(ReservationRequestDto reservationDto, BindingResult result);
}
