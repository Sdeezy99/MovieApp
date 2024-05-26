package com.example.BackendMovie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfiguration {

        private final JwtAuthenticationFilter jwtAuthFilter;//A custom filter for processing JWT tokens in incoming requests.

        private final AuthenticationProvider authenticationProvider;//for retrieving user details and performing authentication.

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
//Specifies which endpoints are publicly accessible and which ones require authentication.
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/v1/auth/**","/api/v1/auth/register","/api/v1/auth/login","/api/v1/auth/movie")
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    //erver does not maintain session information; each request must contain all the necessary authentication information.
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


            return http.build();
        }
    }

