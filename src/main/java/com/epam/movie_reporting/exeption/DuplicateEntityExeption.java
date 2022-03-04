package com.epam.movie_reporting.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateEntityExeption extends RuntimeException {
    public DuplicateEntityExeption(String message) {
        super(message);
    }


}
