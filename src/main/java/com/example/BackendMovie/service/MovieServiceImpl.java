package com.example.BackendMovie.service;

import com.example.BackendMovie.entity.Movie;
import com.example.BackendMovie.repository.MovieRepository;
import com.example.BackendMovie.repository.UserRepository;
import com.example.BackendMovie.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public void updateMovies(Long id, Movie movie) {
        Movie movieFromDb = movieRepository.findById(id).get();
        movieFromDb.setTitle(movie.getTitle());
        movieFromDb.setDescription(movie.getDescription());
        movieFromDb.setRating(movie.getRating());
        movieFromDb.setImage(movie.getImage());
        movieRepository.save(movieFromDb);
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> Optional=movieRepository.findById(id);
        Movie movie;

        if(Optional.isPresent()){
            movie=Optional.get();
        }else {
            throw new RuntimeException("Id not found");
        }
        return movie;
    }
    @Override
    public List<Movie> getMoviesByUserId(Long id) {
        return movieRepository.findByUserId(id);
    }

    @Override
    public void deleteMoviesByUserId(Long id, Long movieId) {
        User user=userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
        List<Movie>userMovies= (List<Movie>) user.getMovies();
        userMovies.removeIf(movie -> movie.getId().equals(movieId));
        userRepository.save(user);
        //return this.movieRepository.deleteMoviesByUserId(id);

    }

    @Override
    public void DeleteMovie(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public void saveMovies(Movie movie, Long id) {

        this.movieRepository.save(movie);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // Check if the movie already exists in the user's watchlist
        boolean movieExists = user.getMovies().stream()
                .anyMatch(m -> m.getTitle().equals(movie.getTitle()));

        if (movieExists) {
            throw new IllegalArgumentException("Movie already exists in the user's watchlist");
        }

        movieRepository.save(movie);
        user.getMovies().add(movie);
        userRepository.save(user);


    }

}
