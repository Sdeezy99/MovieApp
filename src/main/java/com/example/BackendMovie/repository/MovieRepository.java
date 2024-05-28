package com.example.BackendMovie.repository;

import com.example.BackendMovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByUserId(Long id);
    List<Movie>deleteMoviesByUserId(Long id);

}
