package ru.otus.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        ResultSetMetaData rsmd = rs.getMetaData();
        book.setId(rs.getLong(1));
        book.setName(rs.getString(2));
        if (rsmd.getColumnCount() > 2) {

            Author author = new Author();
            author.setId(rs.getLong(3));
            author.setName(rs.getString(4));
            if (author.getId() != null && author.getId() != 0L) {
                book.setAuthors(List.of(author));
            }
            Genre genre = new Genre();
            genre.setId(rs.getLong(5));
            genre.setName(rs.getString(6));
            if (genre.getId() != null && genre.getId() != 0L) {
                book.setGenres(List.of(genre));
            }

        }
        return book;
    }
}
