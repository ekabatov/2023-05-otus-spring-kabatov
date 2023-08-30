package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findAll();
}
