package com.example.BackendMovie.service;

import com.example.BackendMovie.entity.Movie;

import java.util.List;
import java.util.Optional;


public interface MovieService {
    List<Movie> getAllMovies();
    void updateMovies(Long id, Movie movie);
    Optional<Movie> getMoviesByUserId(Long id);
    Movie  getMovieById(Long id);
    void DeleteMovie(Long id);

    void saveMovies(Movie movie, Long id);
}
