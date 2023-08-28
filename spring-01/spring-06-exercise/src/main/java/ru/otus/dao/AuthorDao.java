package ru.otus.dao;

import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
