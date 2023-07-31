package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
