package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.validators.BindingValidator;
import com.example.ticketbookingapp.models.Ticket;
import com.example.ticketbookingapp.repositories.TicketRepository;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.ITicketService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    private final TicketRepository repository;
    private final IFindEntityService findEntityService;

    public TicketService(TicketRepository repository, IFindEntityService findEntityService) {
        this.repository = repository;
        this.findEntityService = findEntityService;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }

    @Override
    public Ticket getTicket(Long id) {
        return findEntityService.findEntityOrThrowException(id, repository, Ticket.class);
    }

    @Override
    public Ticket addTicket(Ticket newTicket, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        return repository.save(newTicket);
    }

    @Override
    public Ticket updateTicket(Ticket updatedTicket, Long id, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        Ticket ticket = findEntityService.findEntityOrThrowException(id, repository, Ticket.class);
        ticket.setType(updatedTicket.getType());
        ticket.setPrice(updatedTicket.getPrice());
        return repository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        repository.delete(findEntityService.findEntityOrThrowException(id, repository, Ticket.class));
    }

    /*private Ticket findTicketOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("There isn't any ticket with id=" + id + " in the database."));
    }*/
}
