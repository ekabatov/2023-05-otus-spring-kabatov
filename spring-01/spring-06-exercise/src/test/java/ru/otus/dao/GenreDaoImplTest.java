package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.otus.dao.mapper.GenreMapper;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GenreDaoImplTest {

    @Mock
    NamedParameterJdbcTemplate jdbcTemplate;

    @InjectMocks
    GenreDaoImpl genreDao;

    Genre genre;

    Book book;

    @BeforeEach
    public void setUp() {
        genre = new Genre();
        genre.setId(1L);
        genre.setName("Name");

        book = new Book();
        book.setId(1L);
        book.setName("Name");
    }

    @Test
    void shouldFindById_whenGiveId() {

        given(jdbcTemplate.queryForObject(eq("SELECT ID, NAME FROM GENRE WHERE ID = :id"), isA(SqlParameterSource.class), isA(GenreMapper.class)))
                .willReturn(genre);
        assertThat(genreDao.findById(genre.getId()))
                .isEqualTo(Optional.of(genre));
    }

    @Test
    void shouldFindByBook_whenGiveBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);

        given(jdbcTemplate.query(
                eq("SELECT ID, NAME FROM GENRE G JOIN BOOK_GENRE BG ON G.ID=BG.GENRE_ID WHERE BG.BOOK_ID=:id"),
                isA(SqlParameterSource.class),
                isA(GenreMapper.class)))
                .willReturn(genres);
        assertThat(genreDao.findGenresByBook(book))
                .isEqualTo(genres);
    }

    @Test
    void shouldFindAll() {
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);

        given(jdbcTemplate.query(
                eq("SELECT ID, NAME FROM GENRE"),
                isA(SqlParameterSource.class),
                isA(GenreMapper.class)))
                .willReturn(genres);
        assertThat(genreDao.findAll())
                .isEqualTo(genres);
    }
}