package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.screeningRoom.idRoom = ?3 AND s.seatNumber = ?1 AND s.rowNumber = ?2")
    Optional<Seat> getSeatBySeatAndRowNumber(int seatNumber, int rowNumber, Long idRoom);
}
