package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAuthorsByBook(Book book) {

//        em.createQuery("select ID, NAME from AUTHOR s left join fetch s.emails", OtusStudent.class);
//        SqlParameterSource namedParameters = new MapSqlParameterSource()
//                .addValue("id", book.getId());
//        return jdbcTemplate.query(
//                "SELECT ID, NAME FROM AUTHOR A JOIN BOOK_AUTHOR BA ON A.ID=BA.AUTHOR_ID WHERE BA.BOOK_ID=:id",
//                namedParameters,
//                new AuthorMapper());
        return null;
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }
}
