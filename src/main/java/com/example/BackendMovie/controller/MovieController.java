package com.example.BackendMovie.controller;

import com.example.BackendMovie.entity.Movie;
import com.example.BackendMovie.repository.UserRepository;
import com.example.BackendMovie.service.MovieServiceImpl;
import com.example.BackendMovie.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/movie")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.getAllMovies();
    }


    @PostMapping("/{id}")
    public void save(@RequestBody Movie movie, @PathVariable Long id) {
        movieService.saveMovies(movie, id);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    @GetMapping("/{id}")
    public Movie findOneById(@PathVariable Long id) {

        return movieService.getMovieById(id);
    }

    @GetMapping("/user/{id}")
    public List<Movie> getMoviesByUserId(@PathVariable Long id) {
        return movieService.getMoviesByUserId(id);
    }

    @PutMapping("/{id}")
    public void updateM(@PathVariable Long id, @RequestBody Movie movie) {
        this.movieService.updateMovies(id, movie);
    }

    @DeleteMapping("/movie/{id}")
    public void delete(@PathVariable Long id) {
        this.movieService.DeleteMovie(id);
    }

}
