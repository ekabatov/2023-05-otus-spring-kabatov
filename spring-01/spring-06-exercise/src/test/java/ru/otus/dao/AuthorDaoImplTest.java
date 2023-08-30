package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class, CommentDaoImpl.class})
class AuthorDaoImplTest {

    @Autowired
    AuthorDaoImpl authorDao;

    List<Author> authors;

    Author tolstoy;

    @BeforeEach
    public void setUp() {
        tolstoy = new Author();
        tolstoy.setId(1L);
        tolstoy.setName("Tolstoy");

        Author chekhov = new Author();
        chekhov.setId(2L);
        chekhov.setName("Chekhov");

        Author twen = new Author();
        twen.setId(3L);
        twen.setName("Twen");

        authors = new ArrayList<>();
        authors.add(tolstoy);
        authors.add(chekhov);
        authors.add(twen);
    }

    @Test
    void shouldFindById_whenGiveId() {
        Optional<Author> authorById = authorDao.findById(tolstoy.getId());
        assertEquals(authorById.get(), tolstoy);
    }

    @Test
    void shouldFindAll() {
        List<Author> all = authorDao.findAll();
        assertEquals(all, authors);
    }
}