package ru.otus.service;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(Long id);

    void save(Comment comment);

    void update(Comment comment);

    void deleteById(long id);

    List<Comment> findByBook(Book book);
}
