package com.example.ticketbookingapp.controllers;

import com.example.ticketbookingapp.models.dtos.requests.ReservationRequestDto;
import com.example.ticketbookingapp.services.interfaces.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {
    private final IReservationService service;

    public ReservationController(IReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity addReservation(@RequestBody @Valid ReservationRequestDto reservationRequestDto, BindingResult result){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addReservation(reservationRequestDto, result));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }
}
