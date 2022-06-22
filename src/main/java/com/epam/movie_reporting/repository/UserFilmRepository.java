package com.epam.movie_reporting.repository;

import com.epam.movie_reporting.model.UserFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {
    @Query(value = "SELECT * FROM user_film  WHERE film_id=:id",nativeQuery = true)
    Set<UserFilm> findByFilm(long id);
}
