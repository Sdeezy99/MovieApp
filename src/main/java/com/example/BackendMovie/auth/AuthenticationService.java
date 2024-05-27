package com.example.BackendMovie.auth;

import com.example.BackendMovie.config.JwtService;
import com.example.BackendMovie.repository.UserRepository;
import com.example.BackendMovie.user.Role;
import com.example.BackendMovie.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {


        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);
//            var jwtToken = jwtService.generateToken(user);
            var jwtToken = jwtService.generateTokenWithId(user, user.getId());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(

                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
//            var jwtToken = jwtService.generateToken(user);
            var jwtToken = jwtService.generateTokenWithId(user, user.getId());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public User findById(Long id) {
            var user = repository.findById(id)
                    .orElseThrow();
            return user;
//            var jwtToken = jwtService.generateToken(user);
//            return AuthenticationResponse.builder()
//                    .token(jwtToken)
//                    .build();
        }


    public User updateUser(Long id, User updatedUser) {
        // Check if the user with the given ID exists
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser != null) {
            // Update the existing user with the new information
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setFirstname(updatedUser.getFirstname());
            existingUser.setEmail(updatedUser.getEmail());
            // Add other fields to update as needed

            // Save the updated user
            return repository.save(existingUser);
        }
        return null; // Return
    }}

