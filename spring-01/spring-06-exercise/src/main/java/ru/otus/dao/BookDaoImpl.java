package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@NamedEntityGraph(
        name = "post-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("subject"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode("comments"),
        }
)
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book save(Book book) {
        em.merge(book);
        return book;
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
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }
}
