package com.epam.movie_reporting.repository;

import com.epam.movie_reporting.model.UserFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {
}
