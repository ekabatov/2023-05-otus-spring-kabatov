package ru.otus.dao;

import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    List<Genre> findGenresByBook(Book book);
}
