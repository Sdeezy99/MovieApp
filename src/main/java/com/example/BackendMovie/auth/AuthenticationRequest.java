package com.example.BackendMovie.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data//generates getters and setters
    @Builder//Provides the Builder pattern for the class, allows you to create instances of AuthenticationRequest
    @AllArgsConstructor//generates a constructor with parameters for all fields.
    @NoArgsConstructor//Generates a no-argument constructor.

    public class AuthenticationRequest {

        //initializes the variables
        private Long id;
        private String email;
        private String password;
    }

