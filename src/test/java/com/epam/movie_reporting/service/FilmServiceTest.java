package com.epam.movie_reporting.service;

import com.epam.movie_reporting.config.mapper.filmMapper.FilmRequestMapper;
import com.epam.movie_reporting.config.mapper.filmMapper.FilmResponseMapper;
import com.epam.movie_reporting.dto.FilmRequestDTO;
import com.epam.movie_reporting.dto.FilmResponseDTO;
import com.epam.movie_reporting.exeption.NotFoundException;
import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.repository.FilmRepository;
import com.epam.movie_reporting.repository.UserFilmRepository;
import com.epam.movie_reporting.util.FilmTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class FilmServiceTest extends FilmTestHelper {


    FilmRepository filmRepository = Mockito.mock(FilmRepository.class);

    FilmRequestMapper requestMapper = Mockito.mock(FilmRequestMapper.class);
    FilmResponseMapper responseMapper = Mockito.mock(FilmResponseMapper.class);

    private FilmService filmService;

    private Film film;
    private FilmResponseDTO responseDto;
    private FilmRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        filmService = new FilmService(filmRepository, requestMapper, responseMapper);
        requestDto = generateFilmRequestDTO();
        responseDto = generateFilmResponseDTO();
        film = generateFilm();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void saveTest() {
        when(requestMapper.mapToEntity(any(FilmRequestDTO.class))).thenReturn(film);
        when(filmRepository.save(any(Film.class))).thenReturn(film);
        when(responseMapper.mapToDTO(any(Film.class))).thenReturn(responseDto);

        filmService.save(requestDto);
        verify(filmRepository, times(1)).save(any(Film.class));
    }

    @Test
    void updateTest() {
        when(filmRepository.findById(any(Long.class))).thenReturn(Optional.of(film));
        when(requestMapper.mapToEntity(any(FilmRequestDTO.class))).thenReturn(film);
        when(responseMapper.mapToDTO(any(Film.class))).thenReturn(responseDto);

        assertEquals(film.getName(), requestDto.getName());
        assertEquals(film.getAgeRestriction(), requestDto.getAgeRestriction());
        filmService.update(requestDto, 1L);
        verify(filmRepository, times(1)).save(any(Film.class));

    }

    @Test
    public void deleteTest() {
        when(filmRepository.findById(any(Long.class))).thenReturn(Optional.of(film));
        filmService.delete(1L);
        verify(filmRepository, times(1)).deleteById(1L);
    }

    @Test
    public void notFoundExceptionTest() {
        when(filmRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> filmService.delete(anyLong()));
        Assertions.assertThrows(NotFoundException.class, () -> filmService.update(requestDto, anyLong()));
    }

}
