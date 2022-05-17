package com.example.ticketbookingapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
    @NotNull(message = "Title cannot be null.")
    private String title;
    @NotNull(message = "Release date cannot be null.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @NotNull(message = "Length cannot be null.")
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration length;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference("movie-screening")
    private List<Screening> screenings = new ArrayList<>();

    public Movie(String title, LocalDate releaseDate, Duration length) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
    }

    public Movie() {
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Duration getLength() {
        return length;
    }

    public void setLength(Duration length) {
        this.length = length;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                '}';
    }
}
