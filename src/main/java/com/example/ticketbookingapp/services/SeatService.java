package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.ScreeningSeats;
import com.example.ticketbookingapp.models.Seat;
import com.example.ticketbookingapp.models.dtos.SeatsDto;
import com.example.ticketbookingapp.repositories.ScreeningSeatsRepository;
import com.example.ticketbookingapp.repositories.SeatRepository;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.ISeatService;
import com.example.ticketbookingapp.validators.SeatValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SeatService implements ISeatService {
    private final SeatRepository seatRepository;
    private final ScreeningSeatsRepository screeningSeatsRepository;
    private final IFindEntityService findEntityService;
    private final SeatValidator validator;

    public SeatService(SeatRepository seatRepository, ScreeningSeatsRepository screeningSeatsRepository, IFindEntityService findEntityService, SeatValidator validator) {
        this.seatRepository = seatRepository;
        this.screeningSeatsRepository = screeningSeatsRepository;
        this.findEntityService = findEntityService;
        this.validator = validator;
    }

    public void reserveSeats(Screening screening, List<SeatsDto> seatsToReserve){
        validator.hasAnUnoccupiedEmptySeatBetween(seatsToReserve, screening, seatRepository, screeningSeatsRepository);
        for (SeatsDto seatDto : seatsToReserve) {
            Seat seat = seatRepository.getSeatBySeatAndRowNumber(seatDto.getSeatNumber(), seatDto.getRowNumber(), screening.getScreeningRoom().getIdRoom()).get();
            ScreeningSeats seatToReserve = screeningSeatsRepository
                    .findScreeningSeatByCompositeKey(screening.getIdScreening(), seat.getIdSeat())
                    .orElseThrow(() -> new NoSuchElementException("There isn't any screening seat with screeningId=" + screening.getIdScreening() + " and seatId=" + seat.getIdSeat() + " in the database."));

            validator.isOccupied(seatToReserve);
            seatToReserve.setOccupied(true);
            screeningSeatsRepository.save(seatToReserve);
        }
    }
}
