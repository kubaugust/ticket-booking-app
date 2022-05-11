package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.ScreeningSeats;
import com.example.ticketbookingapp.models.keys.ScreeningSeatsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScreeningSeatsRepository extends JpaRepository<ScreeningSeats, ScreeningSeatsPK> {
    @Query("SELECT s FROM ScreeningSeats s WHERE s.idScreening = ?1 AND s.idSeat = ?2")
    Optional<ScreeningSeats> findScreeningSeatByCompositeKey(Long idScreening, Long idSeat);
}
