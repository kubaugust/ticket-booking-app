package com.example.ticketbookingapp.controllers;

import com.example.ticketbookingapp.models.ScreeningRoom;
import com.example.ticketbookingapp.services.interfaces.IScreeningRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/screening-rooms")
public class ScreeningRoomController {

    private final IScreeningRoomService service;

    public ScreeningRoomController(IScreeningRoomService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ScreeningRoom>> getAllScreeningRooms() {
        return ResponseEntity.ok(service.getAllScreeningRooms());
    }

    @GetMapping("{id}")
    public ResponseEntity getScreeningRoom(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getScreeningRoom(id));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addScreeningRoom(@Valid @RequestBody ScreeningRoom newScreeningRoom, BindingResult result) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addScreeningRoom(newScreeningRoom, result));
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateScreeningRoom(@Valid @RequestBody ScreeningRoom updatedScreeningRoom, @PathVariable Long id, BindingResult result) {
        try {
            return ResponseEntity.ok(service.updateScreeningRoom(updatedScreeningRoom, id, result));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteScreeningRoom(@PathVariable Long id) {
        try {
            service.deleteScreeningRoom(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }
}
