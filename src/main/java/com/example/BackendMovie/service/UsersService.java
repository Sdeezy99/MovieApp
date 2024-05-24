package com.example.BackendMovie.service;

import com.example.BackendMovie.user.User;

import java.util.Optional;

public interface UsersService {
    Optional<User> getUserById(Long id);

    //    User getUserById(Integer id);
    void saveMovies(User user);
}
