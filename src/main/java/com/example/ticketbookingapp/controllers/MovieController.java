package com.example.ticketbookingapp.controllers;

import com.example.ticketbookingapp.models.Movie;
import com.example.ticketbookingapp.services.interfaces.IMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    private final IMovieService service;

    public MovieController(IMovieService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }

    @GetMapping("{id}")
    public ResponseEntity getMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getMovie(id));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addMovie(@Valid @RequestBody Movie newMovie, BindingResult result) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addMovie(newMovie, result));
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateMovie(@Valid @RequestBody Movie updatedMovie, @PathVariable Long id, BindingResult result) {
        try {
            return ResponseEntity.ok(service.updateMovie(updatedMovie, id, result));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id) {
        try {
            service.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
        }
    }
}
