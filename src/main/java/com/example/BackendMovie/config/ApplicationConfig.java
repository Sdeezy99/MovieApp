package com.example.BackendMovie.config;


import com.example.BackendMovie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration//Indicates that the class declares one or more @Bean methods
    @RequiredArgsConstructor
    public class ApplicationConfig {

        private final UserRepository repository;//for accessing User data from the database.

        @Bean
        //UserDetailsService, which is a core interface in Spring Security.It loads user-specific data.
        public UserDetailsService userDetailsService() {
            return username -> repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }

        @Bean
        //Uses DaoAuthenticationProvider to retrieve user details and verify passwords.
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

        @Bean
        //encoding password
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }

//purpose of this class: It ensures that user details are correctly loaded, passwords are securely hashed, and authentication requests are properly handled.