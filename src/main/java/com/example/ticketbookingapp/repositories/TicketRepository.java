package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.type = ?1")
    Optional<Ticket> getTicketByTypeName(String ticketType);
}
