package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
