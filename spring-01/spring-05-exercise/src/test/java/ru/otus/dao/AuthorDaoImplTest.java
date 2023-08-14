package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.otus.dao.mapper.AuthorMapper;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthorDaoImplTest {

    @Mock
    NamedParameterJdbcTemplate jdbcTemplate;

    @InjectMocks
    AuthorDaoImpl authorDao;

    Author author;

    Book book;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Name");

        book = new Book();
        book.setId(1L);
        book.setName("Name");
    }

    @Test
    void shouldFindById_whenGiveId() {

        given(jdbcTemplate.queryForObject(eq("SELECT ID, NAME FROM AUTHOR WHERE ID = :id"), isA(SqlParameterSource.class), isA(AuthorMapper.class)))
                .willReturn(author);
        assertThat(authorDao.findById(author.getId()))
                .isEqualTo(Optional.of(author));
    }

    @Test
    void shouldFindByBook_whenGiveBook() {
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        given(jdbcTemplate.query(
                eq("SELECT ID, NAME FROM AUTHOR A JOIN BOOK_AUTHOR BA ON A.ID=BA.AUTHOR_ID WHERE BA.BOOK_ID=:id"),
                isA(SqlParameterSource.class),
                isA(AuthorMapper.class)))
                .willReturn(authors);
        assertThat(authorDao.findAuthorsByBook(book))
                .isEqualTo(authors);
    }

    @Test
    void shouldFindAll() {
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        given(jdbcTemplate.query(
                eq("SELECT ID, NAME FROM AUTHOR"),
                isA(SqlParameterSource.class),
                isA(AuthorMapper.class)))
                .willReturn(authors);
        assertThat(authorDao.findAll())
                .isEqualTo(authors);
    }
}