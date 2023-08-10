package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Long id);

    List<Genre> findAll();
}
