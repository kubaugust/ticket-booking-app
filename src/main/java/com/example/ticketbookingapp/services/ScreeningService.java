package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.dtos.SeatsDto;
import com.example.ticketbookingapp.models.dtos.requests.ScreeningRequestDto;
import com.example.ticketbookingapp.models.dtos.responses.ScreeningInfoResponseDto;
import com.example.ticketbookingapp.models.dtos.responses.ScreeningsResponseDto;
import com.example.ticketbookingapp.repositories.MovieRepository;
import com.example.ticketbookingapp.repositories.ScreeningRepository;
import com.example.ticketbookingapp.repositories.ScreeningRoomRepository;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.IScreeningService;
import com.example.ticketbookingapp.validators.BindingValidator;
import com.example.ticketbookingapp.validators.ScreeningValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningService implements IScreeningService {
    
    private final ScreeningRepository repository;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final IFindEntityService findEntityService;
    private final ScreeningValidator validator;

    public ScreeningService(ScreeningRepository repository, MovieRepository movieRepository, ScreeningRoomRepository screeningRoomRepository, IFindEntityService findEntityService, ScreeningValidator validator) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.screeningRoomRepository = screeningRoomRepository;
        this.findEntityService = findEntityService;
        this.validator = validator;
    }

    @Override
    public List<ScreeningsResponseDto> getAllScreenings(LocalDateTime startTime, LocalDateTime endTime) {
        return repository.findAll().stream()
                .filter(screening -> (screening.getStartTime().isAfter(startTime) || screening.getStartTime().isEqual(startTime)) && (screening.getStartTime().isBefore(endTime) || screening.getStartTime().isEqual(endTime)))
                .sorted(Comparator.comparing(Screening::getStartTime).thenComparing((Screening s) -> s.getMovie().getTitle()))
                .map(s -> new ScreeningsResponseDto(s.getIdScreening(), s.getMovie().getTitle(), s.getStartTime()))
                .collect(Collectors.toList());
    }

    @Override
    public ScreeningInfoResponseDto getScreening(Long id) {
        Screening screening = findEntityService.findEntityOrThrowException(id, repository, Screening.class);
        List<SeatsDto> availableSeats = screening.getScreeningSeats().stream()
                .filter(s -> !s.isOccupied())
                .map(s -> new SeatsDto(s.getSeat().getSeatNumber(), s.getSeat().getRowNumber()))
                .collect(Collectors.toList());
        return new ScreeningInfoResponseDto(screening.getMovie().getTitle(), screening.getStartTime(), screening.getScreeningRoom().getNumber(), availableSeats);
    }

    @Override
    public Screening addScreening(ScreeningRequestDto newScreening, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        validator.isOverlappingWithAnotherScreening(newScreening, repository, movieRepository, screeningRoomRepository);
        Screening s = new Screening(newScreening.getStartTime(), movieRepository.findMovieByTitle(newScreening.getMovieTitle()).get(), screeningRoomRepository.findScreeningRoomByNumber(newScreening.getScreeningRoomNumber()).get());
        return repository.save(s);
    }
}
