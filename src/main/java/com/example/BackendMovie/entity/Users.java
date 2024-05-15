package com.example.BackendMovie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @NonNull
    private String confirmPassword;

}
