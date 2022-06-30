package com.epam.movie_reporting.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmRequestDTO {
    private String name;
    private int ageRestriction;
}
