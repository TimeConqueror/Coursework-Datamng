package ru.timeconqueror.coursework.service;

import ru.timeconqueror.coursework.model.Cinema;

import java.util.Optional;
import java.util.UUID;

public interface SimpleService<E> {
    E save(E e);

    void delete(E e);

    Iterable<E> findAll();

    Optional<E> findById(UUID id);
}
