package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.helpers.AmountAndNumberOfTicketsWrapper;
import com.example.ticketbookingapp.models.*;
import com.example.ticketbookingapp.models.dtos.requests.ReservationRequestDto;
import com.example.ticketbookingapp.models.dtos.ReservationTicketDto;
import com.example.ticketbookingapp.models.dtos.SeatsDto;
import com.example.ticketbookingapp.models.dtos.responses.ReservationResponseDto;
import com.example.ticketbookingapp.repositories.*;
import com.example.ticketbookingapp.services.interfaces.ICurrentTimeService;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.IReservationService;
import com.example.ticketbookingapp.services.interfaces.ISeatService;
import com.example.ticketbookingapp.validators.BindingValidator;
import com.example.ticketbookingapp.validators.ReservationValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    private final ReservationRepository repository;
    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;
    private final ReservationTicketsRepository reservationTicketsRepository;
    private final TicketRepository ticketRepository;
    private final ISeatService seatService;
    private final ICurrentTimeService currentTimeService;
    private final IFindEntityService findEntityService;
    private final ReservationValidator validator;
    private final SeatRepository seatRepository;
    private final long minutesToExpire = 15L;


    public ReservationService(ReservationRepository repository, UserRepository userRepository, ScreeningRepository screeningRepository, ReservationTicketsRepository reservationTicketsRepository, TicketRepository ticketRepository, ISeatService seatService, ICurrentTimeService currentTimeService, IFindEntityService findEntityService, ReservationValidator validator, SeatRepository seatRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.screeningRepository = screeningRepository;
        this.reservationTicketsRepository = reservationTicketsRepository;
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.currentTimeService = currentTimeService;
        this.findEntityService = findEntityService;
        this.validator = validator;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public ReservationResponseDto addReservation(ReservationRequestDto reservationDto, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        User user = findEntityService.findEntityOrThrowException(reservationDto.getIdUser(), userRepository, User.class);
        validator.hasUserWithProvidedName(user, reservationDto.getName(), reservationDto.getSurname());

        List<ReservationTicketDto> ticketsToReserve = reservationDto.getReservationTickets();
        validator.hasValidTicketsNames(ticketRepository, ticketsToReserve);

        List<SeatsDto> seatsToReserve = reservationDto.getSeats();
        Screening screening = findEntityService.findEntityOrThrowException(reservationDto.getIdScreening(), screeningRepository, Screening.class);
        validator.hasValidSeats(screening.getScreeningRoom().getIdRoom(), seatRepository, seatsToReserve);

        LocalDateTime startTime = screeningRepository.getById(reservationDto.getIdScreening()).getStartTime();
        validator.hasValidBookingTime(startTime);

        AmountAndNumberOfTicketsWrapper wrapper = getAmountAndNumberOfTickets(ticketsToReserve);

        validator.hasEqualNumberOfTicketsAndSeatsToReserve(wrapper, seatsToReserve);
        seatService.reserveSeats(screening, seatsToReserve);

        Reservation reservation = repository.save(new Reservation(wrapper.getAmount(), currentTimeService.getCurrentTime().plusMinutes(minutesToExpire), user, screening));
        reserveTickets(reservation, ticketsToReserve);

        return new ReservationResponseDto(reservation.getAmount(), reservation.getExpirationTime());
    }

    private AmountAndNumberOfTicketsWrapper getAmountAndNumberOfTickets(List<ReservationTicketDto> ticketsToReserve) {
        BigDecimal amount = new BigDecimal(0);
        int numberOfTickets = 0;
        for (ReservationTicketDto ticket : ticketsToReserve) {
            BigDecimal price = ticketRepository.getTicketByTypeName(ticket.getTicketType()).get().getPrice();
            numberOfTickets += ticket.getNumberOfTicketType();
            BigDecimal number = BigDecimal.valueOf(ticket.getNumberOfTicketType());
            amount = amount.add(price.multiply(number));
        }
        return new AmountAndNumberOfTicketsWrapper(amount, numberOfTickets);
    }

    private void reserveTickets(Reservation reservation, List<ReservationTicketDto> ticketsToReserve){
        for (ReservationTicketDto dto : ticketsToReserve) {
            Ticket ticket = ticketRepository.getTicketByTypeName(dto.getTicketType()).get();
            reservationTicketsRepository.save(new ReservationTickets(dto.getNumberOfTicketType(), reservation, ticket));
        }
    }
}
