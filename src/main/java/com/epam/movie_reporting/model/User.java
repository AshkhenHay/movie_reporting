package com.epam.movie_reporting.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;


    public User() {

    }

    public User(int id, String firstName, String lastName, int age, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }


}
