package com.epam.movie_reporting.service;

import com.epam.movie_reporting.exeption.NotFoundExeption;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.model.User;
import com.epam.movie_reporting.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FilmServiceImpl implements GenericService<Film> {
    @Autowired
    private FilmRepository filmRepository;


    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(Film film) {
        return filmRepository.save(film);
    }


    @Override
    public Film update(Film entity) {
        Optional<Film> existingFilm = filmRepository.findById(entity.getId());
        if (existingFilm.isEmpty()) {
            throw new NotFoundExeption("Film not found");
        } else {
            Film film = existingFilm.get();
            film.setName(entity.getName());
            film.setAgeRestriction(entity.getAgeRestriction());
          return   filmRepository.save(film);
        }
    }

    @Override
    public void delete(int id) {
        filmRepository.deleteById(id);
    }
}
