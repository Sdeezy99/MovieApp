package com.example.BackendMovie.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component//Marks the class as a Spring-managed component
    @RequiredArgsConstructor
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtService jwtService;
        private final UserDetailsService userDetailsService;

        @Override
        //method for filtering each http request
        protected void doFilterInternal(
                @NonNull HttpServletRequest request,
                @NonNull HttpServletResponse response,
                @NonNull FilterChain filterChain
        ) throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorisation");
            final String jwt;
            final String userEmail;
//Checks if the header is present and starts with "Bearer ".it extracts the JWT token by removing the "Bearer " prefix.
            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);// todo extract the userEmail from JWT token;

            //checks if the userEmail is not null and if there is no existing authentication
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);//Loads the user details using the UserDetailsService.
                if (jwtService.isTokenValid(jwt, userDetails)) {//Validates the JWT token against the user details.
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
    //purpose: class is a custom filter that processes incoming HTTP requests to authenticate users based on a JWT (JSON Web Token).

