package com.epam.movie_reporting.service;

import com.epam.movie_reporting.config.mapper.filmMapper.FilmRequestMapper;
import com.epam.movie_reporting.config.mapper.filmMapper.FilmResponseMapper;
import com.epam.movie_reporting.dto.FilmRequestDTO;
import com.epam.movie_reporting.dto.FilmResponseDTO;
import com.epam.movie_reporting.exeption.NotFoundException;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FilmServiceImpl implements GenericService<FilmRequestDTO, FilmResponseDTO> {

    private final FilmRepository filmRepository;
    private final FilmRequestMapper filmRequestMapper;
    private final FilmResponseMapper filmResponseMapper;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository,
                           FilmRequestMapper filmRequestMapper,
                           FilmResponseMapper filmResponseMapper) {
        this.filmRepository = filmRepository;
        this.filmResponseMapper = filmResponseMapper;
        this.filmRequestMapper = filmRequestMapper;
    }


    @Override
    public List<FilmResponseDTO> getAll() {
        List<Film> allFilms = filmRepository.findAll();
        return allFilms
                .stream()
                .map(film -> filmResponseMapper.mapToDTO(film))
                .collect(Collectors.toList());
    }

    @Override
    public FilmResponseDTO save(FilmRequestDTO filmRequestDTO) {
        Film film = filmRequestMapper.mapToEntity(filmRequestDTO);
        Film saveFilm = filmRepository.save(film);
        return filmResponseMapper.mapToDTO(saveFilm);
    }


    @Override
    public FilmResponseDTO update(FilmRequestDTO entity, long id) {
        Optional<Film> existingFilm = filmRepository.findById(id);
        if (existingFilm.isEmpty()) {
            throw new NotFoundException("Film not found");
        } else {
            Film film = existingFilm.get();
            film.setName(entity.getName());
            film.setAgeRestriction(entity.getAgeRestriction());
            Film saveFilm = filmRepository.save(film);
            return filmResponseMapper.mapToDTO(saveFilm);
        }
    }

    @Override
    public FilmResponseDTO getById(long id) {
        Optional<Film> existingFilm = filmRepository.findById(id);
        if (!existingFilm.isEmpty()) {
            return filmResponseMapper.mapToDTO(existingFilm.get());
        } else {
            throw new NotFoundException("Film not found");
        }
    }

    @Override
    public void delete(long id) {
        Optional<Film> existingFilm = filmRepository.findById(id);
        if (!existingFilm.isEmpty()) {
            filmRepository.deleteById(id);
        } else {
            throw new NotFoundException("Film not found");
        }
    }


}
