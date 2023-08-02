package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import ru.otus.dao.mapper.AuthorMapper;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Author> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "SELECT ID, NAME FROM AUTHOR WHERE ID = :id", namedParameters, new AuthorMapper()));

    }

    @Override
    public List<Author> findAuthorsByBook(Book book) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", book.getId());
        return jdbcTemplate.query(
                "SELECT ID, NAME FROM AUTHOR WHERE ID IN (SELECT AUTHOR_ID FROM BOOK_AUTHOR WHERE BOOK_ID = :id)",
                namedParameters,
                new AuthorMapper());
    }

    @Override
    public List<Author> findAll() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbcTemplate.query(
                "SELECT ID, NAME FROM AUTHOR", namedParameters, new AuthorMapper());

    }
}
