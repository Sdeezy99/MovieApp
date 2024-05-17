package com.example.BackendMovie.service;

import com.example.BackendMovie.user.User;

public interface UsersService {
    User getUserById(Integer id);
    void saveUser(User user);
}
