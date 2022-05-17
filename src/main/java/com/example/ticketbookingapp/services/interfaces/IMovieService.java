package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.Movie;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    Movie getMovie(Long id);
    Movie addMovie(Movie newMovie, BindingResult result);
    Movie updateMovie(Movie updatedMovie, Long id, BindingResult result);
    void deleteMovie(Long id);
}
