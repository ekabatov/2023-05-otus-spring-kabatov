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
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment save(Comment comment) {
        return em.merge(comment);
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return em.unwrap(Session.class)
                .createQuery("SELECT distinct c FROM Comment c WHERE c.id=:id", Comment.class)
                .setParameter("id", id)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    @Override
    public List<Comment> findByBook(Book book) {
        TypedQuery<Comment> query = em.createQuery("SELECT distinct c FROM Comment c WHERE c.book=:book", Comment.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public void update(Comment comment) {
        em.merge(comment);
    }
}
