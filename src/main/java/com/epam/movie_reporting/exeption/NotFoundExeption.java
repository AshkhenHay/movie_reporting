package com.epam.movie_reporting.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message) {
        super(message);
    }


}
