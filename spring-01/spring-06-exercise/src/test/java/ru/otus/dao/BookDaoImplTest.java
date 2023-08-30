package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class, CommentDaoImpl.class})
class BookDaoImplTest {

    @Autowired
    BookDaoImpl bookDao;

    Book book;

    Book saveBook;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setName("Name");

        Author tolstoy = new Author();
        tolstoy.setId(1L);
        tolstoy.setName("Tolstoy");

        Genre detective = new Genre();
        detective.setId(1L);
        detective.setName("Detective");

        Author checkhov = new Author();
        checkhov.setId(2L);
        checkhov.setName("Chekhov");

        Genre roman = new Genre();
        roman.setId(2L);
        roman.setName("Roman");

        book.setAuthors(Set.of(tolstoy));
        book.setGenres(Set.of(detective));

        saveBook = new Book();
        saveBook.setId(1L);
        saveBook.setAuthors(Set.of(tolstoy, checkhov));
        saveBook.setGenres(Set.of(detective, roman));
        saveBook.setName("Test");
    }

    @Test
    void shouldFindById_whenGiveId() {
        Book bookFromDb = bookDao.save(book);
        Book result = bookDao.findById(bookFromDb.getId()).get();

        assertEquals(bookFromDb, result);
    }

    @Test
    void shouldFindAll() {
        List<Book> booksResult = bookDao.findAll();

        assertEquals(List.of(saveBook), booksResult);
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
        Optional<Book> byId = bookDao.findById(bookFromDb.getId());
        assertFalse(byId.isPresent());
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