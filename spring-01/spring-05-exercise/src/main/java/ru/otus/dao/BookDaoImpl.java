package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.dao.mapper.BookMapper;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private AuthorDao authorDao;

    private GenreDao genreDao;

    @Override
    public Book save(Book book) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", book.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                "insert into book(name) values (:name)",
                parameters,
                keyHolder
        );

        long bookId = (long) keyHolder.getKey();
        book.setId(bookId);

        saveAuthorsOfBook(book);
        saveGenresOfBook(book);
        return findById(bookId).get();
    }

    @Override
    public void deleteById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID = :id", namedParameters);
    }

    @Override
    public Optional<Book> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        Optional<Book> book = Optional.ofNullable(jdbcTemplate.queryForObject(
                "SELECT ID, NAME FROM BOOK WHERE id = :id", namedParameters, new BookMapper()));
        if (book.isPresent()) {
            List<Author> authorsByBook = authorDao.findAuthorsByBook(book.get());
            List<Genre> genresByBook = genreDao.findGenresByBook(book.get());
            book.get().setAuthors(authorsByBook);
            book.get().setGenres(genresByBook);
        } else {
            throw new EntityNotFoundException("Book with id = " + id + " is not found");
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbcTemplate.query(
                "SELECT ID, NAME FROM BOOK", namedParameters, new BookMapper());
    }

    @Override
    public void update(Book book) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", book.getName())
                .addValue("id", book.getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                "update book set name=:name where id=:id",
                parameters,
                keyHolder
        );

        long bookId = (long) keyHolder.getKey();
        book.setId(bookId);

        jdbcTemplate.update(
                "delete from BOOK_AUTHOR where book_id=:id;" +
                        "delete from BOOK_GENRE where book_id=:id;",
                parameters);

        saveAuthorsOfBook(book);
        saveGenresOfBook(book);
    }

    private void saveAuthorsOfBook(Book book) {
        book.getAuthors()
                .forEach(author -> {
                    SqlParameterSource parametersAuthor = new MapSqlParameterSource()
                            .addValue("book_id", book.getId())
                            .addValue("author_id", author.getId());


                    jdbcTemplate.update(
                            "insert into BOOK_AUTHOR(BOOK_ID, AUTHOR_ID) values (:book_id, :author_id)",
                            parametersAuthor
                    );
                });
    }

    private void saveGenresOfBook(Book book) {
        book.getGenres()
                .forEach(genre -> {
                    SqlParameterSource parametersGenre = new MapSqlParameterSource()
                            .addValue("book_id", book.getId())
                            .addValue("genre_id", genre.getId());
                    jdbcTemplate.update(
                            "insert into BOOK_GENRE(BOOK_ID, GENRE_ID) values (:book_id, :genre_id)",
                            parametersGenre
                    );
                });
    }
}
