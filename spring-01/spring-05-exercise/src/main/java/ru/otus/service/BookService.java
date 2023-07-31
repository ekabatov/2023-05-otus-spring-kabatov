package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);

    void save(Book book);

    void update(Book book);

    void deleteById(long id);

    List<Book> findAll();
}
