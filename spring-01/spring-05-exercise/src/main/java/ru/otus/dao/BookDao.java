package ru.otus.dao;


import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    void deleteById(Long id);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void update(Book book);
}
