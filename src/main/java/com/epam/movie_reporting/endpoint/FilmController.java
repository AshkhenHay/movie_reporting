package com.epam.movie_reporting.endpoint;

import com.epam.movie_reporting.model.Film;
import com.epam.movie_reporting.service.FilmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {



    @Autowired
    private FilmServiceImpl filmService;


    @GetMapping("/")
    public List<Film> getAllFilms() {
        return filmService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity saveFilm(@RequestBody Film film) {
        log.info("Film with " + film.getName()+ "  was added");
        return ResponseEntity.ok(filmService.save(film));

    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Film film) {
        log.info("Film data successfully modified");
        return ResponseEntity.ok(filmService.update(film));
    }

    @DeleteMapping("/")
    public void delete(@PathVariable int id) {
        log.info("Film with id " + id + "  was delete");
        filmService.delete(id);

    }

}
