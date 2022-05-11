package com.example.ticketbookingapp.controllers;

import com.example.ticketbookingapp.models.Movie;
import com.example.ticketbookingapp.models.Ticket;
import com.example.ticketbookingapp.services.interfaces.ITicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
    private final ITicketService service;

    public TicketController(ITicketService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(service.getAllTickets());
    }

    @GetMapping("{id}")
    public ResponseEntity getTicket(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTicket(id));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addTicket(@Valid @RequestBody Ticket newTicket, BindingResult result) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addTicket(newTicket, result));
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateTicket(@Valid @RequestBody Ticket updatedTicket, @PathVariable Long id, BindingResult result) {
        try {
            return ResponseEntity.ok(service.updateTicket(updatedTicket, id, result));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTicket(@PathVariable Long id) {
        try {
            service.deleteTicket(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }
}
