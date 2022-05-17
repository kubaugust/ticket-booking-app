package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.Screening;
import com.example.ticketbookingapp.models.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findScreeningsByStartTimeBetweenAndScreeningRoom(LocalDateTime from, LocalDateTime to, ScreeningRoom room);
}
