package com.example.BackendMovie.entity;

import com.example.BackendMovie.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie_watchlist")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String rating;

    @NonNull
    @Column(length = 2000)
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
