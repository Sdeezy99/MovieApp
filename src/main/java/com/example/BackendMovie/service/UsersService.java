package com.example.BackendMovie.service;

import com.example.BackendMovie.user.User;

public interface UsersService {
    User getMovieById(Integer id);
    void saveMovies(User user);
}
