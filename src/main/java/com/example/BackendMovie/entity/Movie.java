package com.example.BackendMovie.entity;

import com.example.BackendMovie.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie_watchlist")
public class Movie {

    @Setter
    @Getter
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

    @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;



//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "_user.email")
//    private User user;

}
