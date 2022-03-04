package com.epam.movie_reporting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDTO {
    private String name;
    private int ageRestriction;
    private int rateAverage;
}
