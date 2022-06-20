package com.epam.movie_reporting.service;

import com.epam.movie_reporting.config.mapper.userFilmMapper.UserFilmRequestMapper;
import com.epam.movie_reporting.config.mapper.userFilmMapper.UserFilmResponseMapper;
import com.epam.movie_reporting.dto.UserFilmRequestDTO;
import com.epam.movie_reporting.dto.UserFilmResponseDTO;
import com.epam.movie_reporting.exeption.NotFoundException;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.model.User;
import com.epam.movie_reporting.model.UserFilm;
import com.epam.movie_reporting.repository.FilmRepository;
import com.epam.movie_reporting.repository.UserFilmRepository;
import com.epam.movie_reporting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFilmService {
    private final UserFilmRepository userFilmRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    private final UserFilmResponseMapper userFilmResponseMapper;

    @Autowired
    public UserFilmService(UserFilmRepository userFilmRepository,
                           UserRepository userRepository,
                           FilmRepository filmRepository,
                           UserFilmResponseMapper userFilmResponseMapper) {
        this.userFilmRepository = userFilmRepository;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
        this.userFilmResponseMapper = userFilmResponseMapper;
    }

    public UserFilmResponseDTO save(long userId, long filmId, int rate) {
        User user = userRepository.findById(userId).get();
        Film film = filmRepository.findById(filmId).get();
        UserFilm userFilm=new UserFilm();
        userFilm.setUser(user);
        userFilm.setFilm(film);
        userFilm.setRate(rate);
        UserFilm save = userFilmRepository.save(userFilm);
        return userFilmResponseMapper.mapToDTO(save);
    }


    public UserFilmResponseDTO update(long id, int rate) {
        Optional<UserFilm> existingUserFilm = userFilmRepository.findById(id);
        if (existingUserFilm.isEmpty()) {
            throw new NotFoundException("UserFilm not found");
        } else {
            UserFilm userFilm = existingUserFilm.get();
            userFilm.setRate(rate);
            return userFilmResponseMapper.mapToDTO(userFilm);
        }
    }


    public void delete(long id) {
        Optional<UserFilm> existingUserFilm = userFilmRepository.findById(id);
        if (!existingUserFilm.isEmpty()) {
            userFilmRepository.deleteById(id);
        } else {
            throw new NotFoundException("UserFilm not found");
        }
    }
}
