package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;
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
        return em.unwrap(Session.class)
                .createQuery("SELECT g FROM Genre g WHERE g.id=:id", Genre.class)
                .setParameter("id", id)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }
}
