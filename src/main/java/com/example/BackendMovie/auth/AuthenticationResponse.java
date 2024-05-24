package com.example.BackendMovie.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthenticationResponse {

        //declaring the token that will be generated when you login
        private String token;

    }

