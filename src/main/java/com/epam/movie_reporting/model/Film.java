package com.epam.movie_reporting.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
@Table(name = "film")
@Entity

public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int ageRestriction;
    private int rateAverage;

    public Film() {
    }

    public Film(int id, String name, int ageRestriction, int rateAverage) {
        this.id = id;
        this.name = name;
        this.ageRestriction = ageRestriction;
        this.rateAverage = rateAverage;
    }


}
