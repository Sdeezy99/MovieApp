package com.example.BackendMovie.service;

import com.example.BackendMovie.entity.Movie;

import java.util.List;


public interface MovieService {
    List<Movie> getAllMovies();
    void updateMovies(Long id, Movie movie);
    List<Movie> getMoviesByUserId(Long id);
    Movie  getMovieById(Long id);
    void deleteMoviesByUserId(Long id, Long movieId);
    void DeleteMovie(Long id);
    void saveMovies(Movie movie, Long id);
}
