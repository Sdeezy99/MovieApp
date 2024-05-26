package com.example.BackendMovie.repository;

import com.example.BackendMovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {


}
