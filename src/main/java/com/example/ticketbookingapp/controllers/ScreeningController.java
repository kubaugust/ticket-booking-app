package com.example.ticketbookingapp.controllers;

import com.example.ticketbookingapp.models.dtos.requests.ScreeningRequestDto;
import com.example.ticketbookingapp.models.dtos.responses.ScreeningsResponseDto;
import com.example.ticketbookingapp.services.interfaces.IScreeningService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/screenings")
public class ScreeningController {
    private final IScreeningService service;

    public ScreeningController(IScreeningService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ScreeningsResponseDto>> getAllScreenings(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok(service.getAllScreenings(startTime, endTime));
    }

    @GetMapping("/{id}")
    public ResponseEntity getScreening(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getScreening(id));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addScreening(@RequestBody @Valid ScreeningRequestDto newScreening, BindingResult result) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addScreening(newScreening, result));
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }
}
