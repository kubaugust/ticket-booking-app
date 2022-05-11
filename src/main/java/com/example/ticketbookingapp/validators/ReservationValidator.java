package com.example.ticketbookingapp.validators;

import com.example.ticketbookingapp.helpers.AmountAndNumberOfTicketsWrapper;
import com.example.ticketbookingapp.models.User;
import com.example.ticketbookingapp.models.dtos.ReservationTicketDto;
import com.example.ticketbookingapp.models.dtos.SeatsDto;
import com.example.ticketbookingapp.repositories.SeatRepository;
import com.example.ticketbookingapp.repositories.TicketRepository;
import com.example.ticketbookingapp.services.interfaces.ICurrentTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ReservationValidator {
    private final ICurrentTimeService currentTimeService;
    private final long minTimeBeforeScreeningBegins = 15L;

    public ReservationValidator(ICurrentTimeService currentTimeService) {
        this.currentTimeService = currentTimeService;
    }

    public void hasValidBookingTime(LocalDateTime startTime) {
        if (currentTimeService.getCurrentTime().plusMinutes(minTimeBeforeScreeningBegins).isAfter(startTime))
            throw new IllegalArgumentException("Tickets must be booked at least 15 minutes before the screening begins.");
    }

    public void hasEqualNumberOfTicketsAndSeatsToReserve(AmountAndNumberOfTicketsWrapper wrapper, List<SeatsDto> seatsToReserve) {
        if (wrapper.getNumberOfTickets() != seatsToReserve.size())
            throw new IllegalArgumentException("Number of tickets booked is not equal to number of seats reserved.");
    }

    public void hasValidTicketsNames(TicketRepository ticketRepository, List<ReservationTicketDto> reservedTickets) {
        for (ReservationTicketDto dto : reservedTickets) {
            ticketRepository.getTicketByTypeName(dto.getTicketType()).orElseThrow(() -> new NoSuchElementException("There isn't any ticket with type=" + dto.getTicketType() + " in the database."));
        }
    }

    public void hasValidSeats(Long idRoom, SeatRepository seatRepository, List<SeatsDto> seatsToReserve) {
        for (SeatsDto dto : seatsToReserve) {
            seatRepository.getSeatBySeatAndRowNumber(dto.getSeatNumber(), dto.getRowNumber(), idRoom).orElseThrow(() -> new NoSuchElementException("There isn't any seat with number=" + dto.getSeatNumber() + " in row=" + dto.getRowNumber() + " in screening room with id=" + idRoom + " in the database."));
        }
    }

    public void hasUserWithProvidedName(User user, String name, String surname) {
        if (!Objects.equals(user.getName(), name) || !Objects.equals(user.getSurname(), surname))
            throw new IllegalArgumentException("There isn't any user with id=" + user.getIdUser() + " and name=" + name + " " + surname + ".");
    }

}
