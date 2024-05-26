package com.example.BackendMovie.auth;

import com.example.BackendMovie.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

   //marks the class as a controller for handling requests
   @RestController
   //This specifies that all routes in this controller will be prefixed with /api/v1/auth.
    @RequestMapping("/api/v1/auth")
   //allows cross-origin from any domain
    @CrossOrigin("*")
   // generates a constructor with required arguments, it will generate a constructor for the AuthenticationService.
    @RequiredArgsConstructor
    public class AuthenticationController {

        //We're calling the AuthenticationService
        private final AuthenticationService service;

        //We're posting the data from the register request to the database
        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(
                // @RequestBody binds the HTTP request body to the RegisterRequest object.
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
   // binds the id path variable to the method parameter.
        @GetMapping("/{id}")
        public ResponseEntity<User> findById(
                @PathVariable Long id
        ) {
            return ResponseEntity.ok(service.findById(id));
        }
}


