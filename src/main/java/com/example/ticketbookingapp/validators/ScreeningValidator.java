package com.example.ticketbookingapp.validators;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.dtos.requests.ScreeningRequestDto;
import com.example.ticketbookingapp.repositories.MovieRepository;
import com.example.ticketbookingapp.repositories.ScreeningRepository;
import com.example.ticketbookingapp.repositories.ScreeningRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScreeningValidator {
    public void isOverlappingWithAnotherScreening(ScreeningRequestDto screening, ScreeningRepository screeningRepository, MovieRepository movieRepository, ScreeningRoomRepository screeningRoomRepository) {
        LocalDateTime startTime = screening.getStartTime();
        LocalDateTime from = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 0, 0);
        LocalDateTime to = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 23, 59);
        List<Screening> screeningsToCheck = screeningRepository.findScreeningsByStartTimeBetweenAndScreeningRoom(from, to, screeningRoomRepository
                .findScreeningRoomByNumber(screening.getScreeningRoomNumber()).orElseThrow(() -> new NoSuchElementException("There isn't any screening room with number=" + screening.getScreeningRoomNumber() + " in the database.")));
        for (Screening s : screeningsToCheck) {
            if (s.getStartTime().isBefore(startTime.plusMinutes(movieRepository.findMovieByTitle(screening.getMovieTitle()).orElseThrow(() -> new NoSuchElementException("There isn't any movie with title=" + screening.getMovieTitle() + " in the database.")).getLength().toMinutes()))
                    && startTime.isBefore(s.getStartTime().plusMinutes(s.getMovie().getLength().toMinutes())))
                throw new IllegalArgumentException("Screening is overlapping in given room with another screening: " + s);
        }
    }
}
