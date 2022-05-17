package com.example.ticketbookingapp.validators;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.ScreeningSeats;
import com.example.ticketbookingapp.models.Seat;
import com.example.ticketbookingapp.models.dtos.SeatsDto;
import com.example.ticketbookingapp.repositories.ScreeningSeatsRepository;
import com.example.ticketbookingapp.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatValidator {

    public void hasAnUnoccupiedEmptySeatBetween(List<SeatsDto> seatsToReserve, Screening screening, SeatRepository seatRepository, ScreeningSeatsRepository screeningSeatsRepository) {
        for (int i = 0; i < seatsToReserve.size() - 1; i++) {
            SeatsDto actualSeat = seatsToReserve.get(i), nextSeat = seatsToReserve.get(i + 1);
            if (actualSeat.getRowNumber() == nextSeat.getRowNumber() && actualSeat.getSeatNumber() + 1 != nextSeat.getSeatNumber()) {
                Seat seatToCheck = seatRepository.getSeatBySeatAndRowNumber(actualSeat.getSeatNumber() + 1, actualSeat.getRowNumber(), screening.getScreeningRoom().getIdRoom()).get();
                if (!screeningSeatsRepository.findScreeningSeatByCompositeKey(screening.getIdScreening(), seatToCheck.getIdSeat()).get().isOccupied())
                    throw new IllegalArgumentException("There is an empty place left over between 2 seats to reserve (" + actualSeat.getSeatNumber() + " and " + nextSeat.getSeatNumber() + " in row " + actualSeat.getRowNumber() + ").");
            }
        }
    }

    public void isOccupied(ScreeningSeats seatToReserve) {
        if (seatToReserve.isOccupied())
            throw new IllegalArgumentException("Seat number=" + seatToReserve.getSeat().getSeatNumber() + " in row=" + seatToReserve.getSeat().getRowNumber() + " is already occupied.");
    }
}
