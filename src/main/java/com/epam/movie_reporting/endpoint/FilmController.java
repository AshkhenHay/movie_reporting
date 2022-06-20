package com.epam.movie_reporting.endpoint;

import com.epam.movie_reporting.dto.FilmRequestDTO;
import com.epam.movie_reporting.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;



    @PostMapping("")
    public ResponseEntity saveFilm(@RequestBody FilmRequestDTO filmRequestDTO) {
        log.info("Film with " + filmRequestDTO.getName() + "  was added");
        return ResponseEntity.ok(filmService.save(filmRequestDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody FilmRequestDTO filmRequestDTO) {
        log.info("Film data successfully modified");
        return ResponseEntity.ok(filmService.update(filmRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        log.info("Film with id " + id + "  was delete");
        filmService.delete(id);

    }

}
