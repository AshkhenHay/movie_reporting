package com.epam.movie_reporting.endpoint;

import com.epam.movie_reporting.dto.UserFilmResponseDTO;
import com.epam.movie_reporting.service.UserFilmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userFilm")
@AllArgsConstructor
public class UserFilmController {


    private final UserFilmService userFilmService;

    @PostMapping("/{userId}/film/{filmId}/{rate}")
    public UserFilmResponseDTO saveUserFilm(@PathVariable("userId") long userId, @PathVariable("filmId") long filmId, @PathVariable int rate) {
        log.info("UserFilm was created ");
        return userFilmService.save(userId, filmId, rate);

    }

    @PatchMapping("/{id}/{rate}")
    public UserFilmResponseDTO update(@PathVariable("id") long id, @PathVariable int rate) {
        log.info("Film's rate was successfully modified  ");
        return userFilmService.update(id, rate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        log.info("UserFilm with id " + id + "  was delete");
        userFilmService.delete(id);

    }
}
