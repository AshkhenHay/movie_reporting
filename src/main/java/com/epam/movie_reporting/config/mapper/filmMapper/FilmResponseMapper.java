package com.epam.movie_reporting.config.mapper.filmMapper;

import com.epam.movie_reporting.config.mapper.BaseMapper;
import com.epam.movie_reporting.dto.FilmResponseDTO;
import com.epam.movie_reporting.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmResponseMapper implements BaseMapper<Film, FilmResponseDTO> {
    @Override
    public FilmResponseDTO mapToDTO(Film film) {
        return FilmResponseDTO.builder()
                .id(film.getId())
                .name(film.getName())
                .ageRestriction(film.getAgeRestriction())
                .build();
    }

    @Override
    public Film mapToEntity(FilmResponseDTO filmResponseDTO) {
        return Film.builder()
                .id(filmResponseDTO.getId())
                .name(filmResponseDTO.getName())
                .ageRestriction(filmResponseDTO.getAgeRestriction())
                .build();
    }
}
