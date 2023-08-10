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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
        List<Book> books = jdbcTemplate.query(
                """
                        SELECT B.ID, B.NAME, A.ID, A.NAME, G.ID, G.NAME
                        FROM BOOK B
                        LEFT JOIN BOOK_AUTHOR BA ON B.ID=BA.BOOK_ID
                        LEFT JOIN AUTHOR A ON A.ID=BA.AUTHOR_ID
                        LEFT JOIN BOOK_GENRE BG ON B.ID=BG.BOOK_ID
                        LEFT JOIN GENRE G ON G.ID=BG.GENRE_ID
                        WHERE B.ID=:id
                        """, namedParameters, new BookMapper());
        books = mergeBooks(books);
        if (books.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(books.get(0));
        }
    }

    @Override
    public List<Book> findAll() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Book> books = jdbcTemplate.query(
                """
                        SELECT B.ID, B.NAME, A.ID, A.NAME, G.ID, G.NAME
                        FROM BOOK B
                        LEFT JOIN BOOK_AUTHOR BA ON B.ID=BA.BOOK_ID
                        LEFT JOIN AUTHOR A ON A.ID=BA.AUTHOR_ID
                        LEFT JOIN BOOK_GENRE BG ON B.ID=BG.BOOK_ID
                        LEFT JOIN GENRE G ON G.ID=BG.GENRE_ID
                        """, namedParameters, new BookMapper());

        return mergeBooks(books);
    }

    private List<Book> mergeBooks(List<Book> books) {
        Map<Long, List<Book>> booksGroupingById = books.stream().
                collect(groupingBy(Book::getId));
        return booksGroupingById.entrySet().stream()
                .map(entry -> {
                    List<Book> booksById = entry.getValue();
                    Set<Author> authors = booksById.stream()
                            .map(Book::getAuthors)
                            .filter(Objects::nonNull)
                            .flatMap(List::stream)
                            .collect(Collectors.toSet());
                    Set<Genre> genres = booksById.stream()
                            .map(Book::getGenres)
                            .filter(Objects::nonNull)
                            .flatMap(List::stream)
                            .collect(Collectors.toSet());
                    Book book = new Book();
                    book.setId(entry.getKey());
                    book.setName(booksById.get(0).getName());
                    book.setAuthors(new ArrayList<>(authors));
                    book.setGenres(new ArrayList<>(genres));
                    return book;
                })
                .collect(Collectors.toList());
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
