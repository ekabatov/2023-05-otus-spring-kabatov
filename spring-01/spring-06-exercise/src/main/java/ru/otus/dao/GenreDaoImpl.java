package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findGenresByBook(Book book) {
//        SqlParameterSource namedParameters = new MapSqlParameterSource()
//                .addValue("id", book.getId());
//        return jdbcTemplate.query(
//                "SELECT ID, NAME FROM GENRE G JOIN BOOK_GENRE BG ON G.ID=BG.GENRE_ID WHERE BG.BOOK_ID=:id",
//                namedParameters,
//                new GenreMapper());
        return null;
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }
}
