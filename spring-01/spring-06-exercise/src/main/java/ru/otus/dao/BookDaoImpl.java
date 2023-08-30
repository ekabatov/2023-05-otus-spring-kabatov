package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book save(Book book) {
        return em.merge(book);
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Book> findById(long id) {
        return em.unwrap(Session.class)
                .createQuery("SELECT distinct b FROM Book b JOIN FETCH b.authors JOIN FETCH b.genres WHERE b.id=:id", Book.class)
                .setParameter("id", id)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT distinct b FROM Book b JOIN FETCH b.authors JOIN FETCH b.genres", Book.class);
        return query.getResultList();
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }
}
