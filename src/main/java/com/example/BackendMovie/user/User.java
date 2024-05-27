package com.example.BackendMovie.user;

import com.example.BackendMovie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Getter
    @Setter
    //naming our table
    @Table(name = "_user")
    //userDetails is a build in method
    public class User implements UserDetails {

        @Id
        //it auto generates user id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        //@Column(name = "id")
        private Long id;

        private String firstname;
        private String email;
        private String password;



        @JsonIgnore
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
       // @JoinColumn(name = "user_id", referencedColumnName = "id")
        private Set<Movie> movies;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }

//getters and setters od userDetails
        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

