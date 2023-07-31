package ru.otus.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("ID"));
        book.setName(rs.getString("NAME"));
        return book;
    }
}
