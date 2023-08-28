package ru.otus.dao;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment save(Comment comment);

    void deleteById(Long id);

    Optional<Comment> findById(long id);

    List<Comment> findByBook(Book book);

    void update(Comment comment);
}
