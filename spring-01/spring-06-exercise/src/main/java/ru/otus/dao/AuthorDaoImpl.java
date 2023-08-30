package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
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
        return em.unwrap(Session.class)
                .createQuery("SELECT a FROM Author a WHERE a.id=:id", Author.class)
                .setParameter("id", id)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }
}
