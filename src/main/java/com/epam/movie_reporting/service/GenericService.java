package com.epam.movie_reporting.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenericService<Q, A> {
    List<A> getAll();

    A save(Q entity);

    A update(Q entity, long id);

    void delete(long id);

    A getById(long id);


}
