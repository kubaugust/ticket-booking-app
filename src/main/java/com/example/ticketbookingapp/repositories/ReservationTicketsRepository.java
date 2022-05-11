package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.ReservationTickets;
import com.example.ticketbookingapp.models.keys.ReservationTicketsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservationTicketsRepository extends JpaRepository<ReservationTickets, ReservationTicketsPK> {
    @Query("SELECT t FROM ReservationTickets t WHERE t.idReservation = ?1 AND t.idTicket = ?2")
    Optional<ReservationTickets> findReservationTicketsByCompositeKey(Long idReservation, Long idTicket);
}
