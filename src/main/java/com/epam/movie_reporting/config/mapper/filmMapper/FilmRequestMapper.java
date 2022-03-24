package com.epam.movie_reporting.config.mapper.filmMapper;

import com.epam.movie_reporting.config.mapper.BaseMapper;
import com.epam.movie_reporting.dto.FilmRequestDTO;
import com.epam.movie_reporting.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmRequestMapper implements BaseMapper<Film, FilmRequestDTO> {
    @Override
    public FilmRequestDTO mapToDTO(Film film) {
        return FilmRequestDTO.builder()
                .name(film.getName())
                .ageRestriction(film.getAgeRestriction())
                .rateAverage(film.getRateAverage())
                .build();
    }

    @Override
    public Film mapToEntity(FilmRequestDTO filmRequestDTO) {
        return Film.builder()
                .name(filmRequestDTO.getName())
                .ageRestriction(filmRequestDTO.getAgeRestriction())
                .rateAverage(filmRequestDTO.getRateAverage())
                .build();
    }
}
