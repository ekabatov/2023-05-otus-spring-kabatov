package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
class BookDaoImplTest {

    @Autowired
    BookDaoImpl bookDao;

    Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setName("Name");

        Author author = new Author();
        author.setId(1L);
        author.setName("Name");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Name");

        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
    }

    @Test
    void shouldFindById_whenGiveId() {
        Book bookFromDb = bookDao.save(book);
        Book result = bookDao.findById(bookFromDb.getId()).get();

        assertEquals(bookFromDb, result);
    }

    @Test
    void shouldFindAll() {
        Book bookFromDb = bookDao.save(book);
        List<Book> books = List.of(bookFromDb);
        List<Book> booksResult = bookDao.findAll();

        assertEquals(books, booksResult);
    }

    @Test
    void shouldSaveBook_whenGiveBook() {
        Book bookFromDb = bookDao.save(book);
        Book result = bookDao.findById(bookFromDb.getId()).get();

        assertEquals(bookFromDb, result);
    }

    @Test
    void shouldDeleteById_whenGiveId() {
        Book bookFromDb = bookDao.save(book);
        bookDao.deleteById(bookFromDb.getId());
        assertEquals(Optional.empty(), bookDao.findById(bookFromDb.getId()));
    }

    @Test
    void shouldUpdateBook_whenGiveBook() {
        Book bookFromDb = bookDao.save(book);
        bookFromDb.setName("Update name");
        bookDao.update(bookFromDb);
        Book bookResult = bookDao.findById(bookFromDb.getId()).get();
        assertEquals(bookFromDb.getName(), bookResult.getName());
    }
}