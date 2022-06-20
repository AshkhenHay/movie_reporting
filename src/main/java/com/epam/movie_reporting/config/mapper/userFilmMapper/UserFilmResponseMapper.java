package com.epam.movie_reporting.config.mapper.userFilmMapper;

import com.epam.movie_reporting.config.mapper.BaseMapper;
import com.epam.movie_reporting.dto.FilmResponseDTO;
import com.epam.movie_reporting.dto.UserFilmResponseDTO;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.model.UserFilm;
import org.springframework.stereotype.Component;

@Component
public class UserFilmResponseMapper implements BaseMapper<UserFilm, UserFilmResponseDTO> {
    @Override
    public UserFilmResponseDTO mapToDTO(UserFilm userFilm) {
        return UserFilmResponseDTO.builder()
                .id(userFilm.getId())
                .user(userFilm.getUser())
                .film(userFilm.getFilm())
                .rate(userFilm.getRate())
                .build();
    }

    @Override
    public UserFilm mapToEntity(UserFilmResponseDTO userFilmResponseDTO) {
        return UserFilm.builder()
                .id(userFilmResponseDTO.getId())
                .user(userFilmResponseDTO.getUser())
                .film(userFilmResponseDTO.getFilm())
                .rate(userFilmResponseDTO.getRate())
                .build();
    }
}
