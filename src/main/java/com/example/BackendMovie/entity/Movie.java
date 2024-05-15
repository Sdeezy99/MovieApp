package com.example.BackendMovie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movieDetails")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String date;

    @NonNull
    private String rating;

    @NonNull
    private String image;
}
