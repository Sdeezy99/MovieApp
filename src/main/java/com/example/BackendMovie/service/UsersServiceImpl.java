package com.example.BackendMovie.service;

import com.example.BackendMovie.repository.UserRepository;
import com.example.BackendMovie.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;


/*    public User geUserById(Long id) {
        Optional<User> Optional=userRepository.findById();
        User user;

        if(Optional.isPresent()){
            user=Optional.get();
        }else {
            throw new RuntimeException("Id not found");
        }
        return user;
    }*/

    public Optional<User> getUserById(Long id) {
        return  userRepository.findById(id);
    }

    @Override
    public void saveMovies(User user) {
        this.userRepository.save(user);
    }
}
