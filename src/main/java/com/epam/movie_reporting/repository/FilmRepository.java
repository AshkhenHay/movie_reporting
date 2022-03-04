package com.epam.movie_reporting.repository;

import com.epam.movie_reporting.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
