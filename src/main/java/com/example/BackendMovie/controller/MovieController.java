package com.example.BackendMovie.controller;

import com.example.BackendMovie.entity.Movie;
import com.example.BackendMovie.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/movie")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping
    public List<Movie> findAll()
    {
        return  movieService.getAllMovies();
    }
    @PostMapping
    public  void save(@RequestBody Movie movie)
    {

        movieService.saveMovies(movie);
    }
    @GetMapping("/{id}")
    public  Movie findOneById(@PathVariable Long id)
    {
        return  movieService.getMovieById(id);
    }
    @PutMapping("/{id}")
    public void updateM(@PathVariable Long id,@RequestBody Movie movie)
    {
        this.movieService.updateMovies(id,movie);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        this.movieService.DeleteMovie(id);
    }

}
