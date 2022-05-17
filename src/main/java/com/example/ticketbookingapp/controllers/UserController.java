package com.example.ticketbookingapp.controllers;

import com.example.ticketbookingapp.services.interfaces.IUserService;
import com.example.ticketbookingapp.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getUser(id));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody User newUser, BindingResult result) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(newUser, result));
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User updatedUser, @PathVariable Long id, BindingResult result) {
        try {
            return ResponseEntity.ok(service.updateUser(updatedUser, id, result));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }
}
