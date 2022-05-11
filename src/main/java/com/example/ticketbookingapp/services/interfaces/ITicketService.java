package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.Ticket;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ITicketService {
    List<Ticket> getAllTickets();
    Ticket getTicket(Long id);
    Ticket addTicket(Ticket newTicket, BindingResult result);
    Ticket updateTicket(Ticket updatedTicket, Long id, BindingResult result);
    void deleteTicket(Long id);
}
