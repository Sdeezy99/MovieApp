package com.example.BackendMovie.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
    public class JwtService {

    //A base64-encoded string used as the secret key for signing and verifying JWTs.
        private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
// Extracts the username from the JWT token.
        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        //Extracts a specific claim from the JWT token.
        public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
            final Claims claims = extractAllClaims(token);
            return claimResolver.apply(claims);
        }
        //Generates a JWT token for the given user details without extra claims.
        public String generateToken(UserDetails userDetails) {
            return generateToken(new HashMap<>(), userDetails);

        }
// Generates a JWT token for the given user details with extra claims.
        public String generateToken(
                Map<String, Object> extraClaims,
                UserDetails userDetails

        ) {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                   // .setSubject(userDetails)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 30000))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
//Validates the JWT token against the user details.
        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        }
// Checks if the JWT token has expired.
        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }
//Extracts the expiration date from the JWT token.
        private Date extractExpiration(String token) {

            return extractClaim(token, Claims::getExpiration);
        }

        private Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Key getSigningKey() {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }
    }

