package ru.otus.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("ID"));
        author.setName(rs.getString("NAME"));
        return author;
    }
}
