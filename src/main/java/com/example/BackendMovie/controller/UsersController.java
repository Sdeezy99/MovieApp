package com.example.BackendMovie.controller;

import com.example.BackendMovie.service.UsersServiceImpl;
import com.example.BackendMovie.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/register/user")
@CrossOrigin("**")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @PostMapping
    public  void save(@RequestBody User user)
    {
        usersService.saveMovies(user);
    }



//    @GetMapping("/{id}")
//    public User findOneById(@PathVariable Long id)
//    {
//        return  usersService.getUserById(id);
//    }
}
