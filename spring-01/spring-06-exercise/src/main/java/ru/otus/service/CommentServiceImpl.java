package ru.otus.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public Optional<Comment> findById(Long id) {
        return commentDao.findById(id);
    }

    @Transactional
    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Transactional
    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Comment> findByBook(Book book) {
        return commentDao.findByBook(book);
    }
}
