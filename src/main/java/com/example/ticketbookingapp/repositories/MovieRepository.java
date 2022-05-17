package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMovieByTitle(@NotNull(message = "Title cannot be null.") String title);
}
