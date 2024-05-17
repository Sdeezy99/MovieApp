package com.example.BackendMovie.service;

import com.example.BackendMovie.repository.UserRepository;
import com.example.BackendMovie.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer id) {
        Optional<User> Optional=userRepository.findById(id);
        User user;

        if(Optional.isPresent()){
            user=Optional.get();
        }else {
            throw new RuntimeException("Id not found");
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
