package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class, CommentDaoImpl.class})
class GenreDaoImplTest {

    @Autowired
    GenreDaoImpl genreDao;

    List<Genre> genres;

    @BeforeEach
    public void setUp() {
        Genre detective = new Genre();
        detective.setId(1L);
        detective.setName("Detective");

        Genre roman = new Genre();
        roman.setId(2L);
        roman.setName("Roman");

        Genre drama = new Genre();
        drama.setId(3L);
        drama.setName("Drama");

        genres = new ArrayList<>();
        genres.add(detective);
        genres.add(roman);
        genres.add(drama);
    }

    @Test
    void shouldFindById_whenGiveId() {
        Optional<Genre> genreyId = genreDao.findById(genres.get(0).getId());
        assertEquals(genreyId.get(), genres.get(0));
    }

    @Test
    void shouldFindAll() {
        List<Genre> all = genreDao.findAll();
        assertEquals(all, genres);
    }
}