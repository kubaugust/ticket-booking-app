package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.dtos.SeatsDto;

import java.util.List;

public interface ISeatService {
    void reserveSeats(Screening screening, List<SeatsDto> seatsToReserve);
}
