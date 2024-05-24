package com.example.BackendMovie.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthenticationRequest {

        //initializes the variables
        private Long id;
        private String email;
        private String password;
    }

