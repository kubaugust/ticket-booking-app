package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.validators.BindingValidator;
import com.example.ticketbookingapp.models.Movie;
import com.example.ticketbookingapp.repositories.MovieRepository;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.IMovieService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository repository;
    private final IFindEntityService findEntityService;

    public MovieService(MovieRepository repository, IFindEntityService findEntityService) {
        this.repository = repository;
        this.findEntityService = findEntityService;
    }

    @Override
    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    @Override
    public Movie getMovie(Long id) {
        return findEntityService.findEntityOrThrowException(id, repository, Movie.class);
    }

    @Override
    public Movie addMovie(Movie newMovie, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        return repository.save(newMovie);
    }

    @Override
    public Movie updateMovie(Movie updatedMovie, Long id, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        Movie movie = findEntityService.findEntityOrThrowException(id, repository, Movie.class);
        movie.setTitle(updatedMovie.getTitle());
        movie.setReleaseDate(updatedMovie.getReleaseDate());
        movie.setLength(updatedMovie.getLength());
        return repository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        repository.delete(findEntityService.findEntityOrThrowException(id, repository, Movie.class));
    }

    /*
    I decided that this type of code duplicates in every EntityService class, so maybe it would be a good idea to create a generic method.
    */
    /*private Movie findMovieOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("There isn't any movie with id=" + id + " in the database."));
    }*/
}
