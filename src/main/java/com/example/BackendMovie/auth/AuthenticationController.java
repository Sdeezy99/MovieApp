package com.example.BackendMovie.auth;

import com.example.BackendMovie.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api/v1/auth")
    @CrossOrigin("*")
    @RequiredArgsConstructor
    public class AuthenticationController {

        //We're calling the AuthenticationService
        private final AuthenticationService service;

        //We're posting the data from the register request to the database
        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody RegisterRequest request
        ) {
            //returns the status of the request(e.g ok means it was successful)
            return ResponseEntity.ok(service.register(request));
        }
    //We're posting the data from the AuthenticationRequest request to the database
        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> authenticate(
                @RequestBody AuthenticationRequest request
        ) {
            //returns the status of the request(e.g ok means it was successful)
            return ResponseEntity.ok(service.authenticate(request));
        }
    //we're getting details of a user using id
        @GetMapping("/{id}")
        public ResponseEntity<User> findById(
                @PathVariable Long id
        ) {
            return ResponseEntity.ok(service.findById(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<AuthenticationResponse> update(
                @RequestBody AuthenticationRequest request
        ) {
            //returns the status of the request(e.g ok means it was successful)
            return ResponseEntity.ok(service.authenticate(request));
        }

}


