package com.example.BackendMovie.service;

import com.example.BackendMovie.entity.Movie;

import java.util.List;


public interface MovieService {
    List<Movie> getAllMovies();
    void updateMovies(Long id, Movie movie);
    Movie  getMovieById(Long id);
    void DeleteMovie(Long id);
    void saveMovies(Movie movie);
}
