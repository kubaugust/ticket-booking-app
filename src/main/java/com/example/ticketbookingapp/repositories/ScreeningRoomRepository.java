package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Long> {
    Optional<ScreeningRoom> findScreeningRoomByNumber(@PositiveOrZero(message = "Screening room number cannot be negative.") int number);
}
