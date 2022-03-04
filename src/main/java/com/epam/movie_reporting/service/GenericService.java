package com.epam.movie_reporting.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GenericService<T> {
    List<T> getAll();

   T save(T entity);

    T update(T entity);

    void delete(int id);




}
