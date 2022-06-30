package com.epam.movie_reporting.util;

import com.epam.movie_reporting.dto.FilmRequestDTO;
import com.epam.movie_reporting.dto.FilmResponseDTO;
import com.epam.movie_reporting.model.Film;


public class FilmTestHelper {


    public static FilmRequestDTO generateFilmRequestDTO() {
        return FilmRequestDTO.builder()
                .name("test_Name")
                .ageRestriction(18)
                .build();
    }

    public static FilmResponseDTO generateFilmResponseDTO() {
        return FilmResponseDTO.builder()
                .id(1L)
                .name("test_Name")
                .ageRestriction(18)
                .build();
    }


    public static Film generateFilm() {
        return Film.builder()
                .id(1L)
                .name("test_Name")
                .ageRestriction(18)
                .build();
    }

}

