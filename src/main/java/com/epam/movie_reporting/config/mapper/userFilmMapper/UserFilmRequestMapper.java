package com.epam.movie_reporting.config.mapper.userFilmMapper;

import com.epam.movie_reporting.config.mapper.BaseMapper;
import com.epam.movie_reporting.dto.FilmRequestDTO;
import com.epam.movie_reporting.dto.UserFilmRequestDTO;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.model.UserFilm;
import org.springframework.stereotype.Component;

@Component
public class UserFilmRequestMapper implements BaseMapper<UserFilm, UserFilmRequestDTO> {
    @Override
    public UserFilmRequestDTO mapToDTO(UserFilm userFilm) {
        return UserFilmRequestDTO.builder()
                .user(userFilm.getUser())
                .film(userFilm.getFilm())
                .rate(userFilm.getRate())
                .build();
    }

    @Override
    public UserFilm mapToEntity(UserFilmRequestDTO userFilmRequestDTO) {
        return UserFilm.builder()
                .user(userFilmRequestDTO.getUser())
                .film(userFilmRequestDTO.getFilm())
                .rate(userFilmRequestDTO.getRate())
                .build();
    }
}
