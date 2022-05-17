package com.example.ticketbookingapp.database;

import com.example.ticketbookingapp.models.*;
import com.example.ticketbookingapp.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
class InitializeDatabase {

    private static final Logger log = LoggerFactory.getLogger(InitializeDatabase.class);

    @Bean
    CommandLineRunner initUsers(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("johndoe@gmail.com","John", "Doe")));
            log.info("Preloading " + repository.save(new User("richardroe@gmail.com","Richard", "Roe")));
        };
    }

    @Bean
    CommandLineRunner initTickets(TicketRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Ticket("adult", BigDecimal.valueOf(25))));
            log.info("Preloading " + repository.save(new Ticket("student", BigDecimal.valueOf(18))));
            log.info("Preloading " + repository.save(new Ticket("child", BigDecimal.valueOf(12.50))));
        };
    }

    @Bean
    CommandLineRunner initMovies(MovieRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Movie("Pulp Fiction", LocalDate.of(1994, 5, 21), Duration.ofMinutes(154))));
            log.info("Preloading " + repository.save(new Movie("Inglorious Basterds", LocalDate.of(2009, 5, 20), Duration.ofMinutes(153))));
            log.info("Preloading " + repository.save(new Movie("Reservoir Dogs", LocalDate.of(1992, 1, 21), Duration.ofMinutes(99))));
        };
    }

    @Bean
    CommandLineRunner initScreeningRooms(ScreeningRoomRepository roomRepository, SeatRepository seatRepository) {
        return args -> {
            ScreeningRoom room1 = new ScreeningRoom(1, 6, 6);
            log.info("Preloading " + roomRepository.save(room1));
            initSeats(room1, seatRepository);
            ScreeningRoom room2 = new ScreeningRoom(2, 7, 7);
            log.info("Preloading " + roomRepository.save(room2));
            initSeats(room2, seatRepository);
            ScreeningRoom room3 = new ScreeningRoom(3, 8, 8);
            log.info("Preloading " + roomRepository.save(room3));
            initSeats(room3, seatRepository);
        };
    }

    private void initSeats(ScreeningRoom room, SeatRepository seatRepository){
        for (int i = 0; i < room.getRows(); i++) {
            for (int j = 0; j < room.getRowSeats(); j++) {
                seatRepository.save(new Seat(j, i, room));
            }
        }
    }

    @Bean
    CommandLineRunner initScreenings(ScreeningRepository repository, MovieRepository movieRepository, ScreeningRoomRepository screeningRoomRepository, SeatRepository seatRepository, ScreeningSeatsRepository screeningSeatsRepository) {
        return args -> {
            Screening s1 = new Screening(startingAt(1, 19, 15), movieRepository.findById(1L).get(), screeningRoomRepository.findById(1L).get());
            log.info("Preloading " + repository.save(s1));
            initScreeningSeats(s1, seatRepository, screeningSeatsRepository);
            Screening s2 = new Screening(startingAt(1, 19, 15), movieRepository.findById(3L).get(), screeningRoomRepository.findById(2L).get());
            log.info("Preloading " + repository.save(s2));
            initScreeningSeats(s2, seatRepository, screeningSeatsRepository);
            Screening s3 = new Screening(startingAt(1, 18, 45), movieRepository.findById(2L).get(), screeningRoomRepository.findById(3L).get());
            log.info("Preloading " + repository.save(s3));
            initScreeningSeats(s3, seatRepository, screeningSeatsRepository);

            Screening s4 = new Screening(startingAt(2, 19, 45), movieRepository.findById(2L).get(), screeningRoomRepository.findById(1L).get());
            log.info("Preloading " + repository.save(s4));
            initScreeningSeats(s4, seatRepository, screeningSeatsRepository);
            Screening s5 = new Screening(startingAt(2, 20, 15), movieRepository.findById(1L).get(), screeningRoomRepository.findById(2L).get());
            log.info("Preloading " + repository.save(s5));
            initScreeningSeats(s5, seatRepository, screeningSeatsRepository);
            Screening s6 = new Screening(startingAt(2, 19, 45), movieRepository.findById(3L).get(), screeningRoomRepository.findById(3L).get());
            log.info("Preloading " + repository.save(s6));
            initScreeningSeats(s6, seatRepository, screeningSeatsRepository);
        };
    }

    private void initScreeningSeats(Screening screening, SeatRepository seatRepository, ScreeningSeatsRepository screeningSeatsRepository){
        for (int i = 0; i < screening.getScreeningRoom().getRows(); i++) {
            for (int j = 0; j < screening.getScreeningRoom().getRowSeats(); j++) {
                Seat seat = seatRepository.getSeatBySeatAndRowNumber(j, i, screening.getScreeningRoom().getIdRoom()).get();
                screeningSeatsRepository.save(new ScreeningSeats(false, screening, seat));
            }
        }
    }

    private LocalDateTime startingAt(int plusDays, int hour, int minutes) {
        return LocalDateTime.of(LocalDate.now().plusDays(plusDays), LocalTime.of(hour, minutes));
    }
}
