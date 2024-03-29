package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import ru.otus.dao.mapper.GenreMapper;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Genre> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "SELECT ID, NAME FROM GENRE WHERE ID = :id", namedParameters, new GenreMapper()));
    }

    @Override
    public List<Genre> findGenresByBook(Book book) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", book.getId());
        return jdbcTemplate.query(
                "SELECT ID, NAME FROM GENRE G JOIN BOOK_GENRE BG ON G.ID=BG.GENRE_ID WHERE BG.BOOK_ID=:id",
                namedParameters,
                new GenreMapper());

    }

    @Override
    public List<Genre> findAll() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbcTemplate.query(
                "SELECT ID, NAME FROM GENRE", namedParameters, new GenreMapper());
    }
}
