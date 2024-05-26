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


        private final UserRepository repository;// For database operations
        private final PasswordEncoder passwordEncoder;// For encoding passwords.
        private final JwtService jwtService;// For generating JWT tokens.
        private final AuthenticationManager authenticationManager;//For managing authentication processes

        public AuthenticationResponse register(RegisterRequest request) {
            var user = User.builder()
                    .id(request.getUserid())
                    .firstname(request.getFirstname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))//Encodes the user's password
                    .role(Role.USER)//Sets the user's role to USER
                    .build();
            repository.save(user);//Saves the user to the database using UserRepository
            var jwtToken = jwtService.generateToken(user);//Generates a JWT token for the newly registered user using JwtService.
            return AuthenticationResponse.builder()
                    .token(jwtToken)//returns a response containing the jwt token
                    .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(//Authenticates the user credentials using AuthenticationManager.
                    new UsernamePasswordAuthenticationToken(

                            request.getEmail(),
                            request.getPassword()
                    )
            );
            //Retrieves the user from the database using the email provided in the AuthenticationRequest.
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
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

//    public AuthenticationResponse updateUserById(Long id) {
//        var user = repository.deleteById(id);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
    }

