package ru.otus.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getLong("ID"));
        genre.setName(rs.getString("NAME"));
        return genre;
    }
}
