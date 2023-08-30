package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class, CommentDaoImpl.class})
public class CommentDaoImplTest {

    @Autowired
    CommentDaoImpl commentDao;

    List<Comment> comments;

    Comment commentTolstoy;

    Comment saveComment;

    Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(1L);
        book.setName("Test");

        Author author = new Author();
        author.setId(1L);
        author.setName("Tolstoy");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Detective");

        book.setAuthors(Set.of(author));
        book.setGenres(Set.of(genre));

        commentTolstoy = new Comment();
        commentTolstoy.setBook(book);
        commentTolstoy.setText("Brilliant");
        comments = new ArrayList<>();
        comments.add(commentTolstoy);

        saveComment = new Comment();
        saveComment.setId(1L);
        saveComment.setBook(book);
        saveComment.setText("So So");
    }

    @Test
    void shouldFindById_whenGiveId() {
        Comment commentFromDB = commentDao.save(commentTolstoy);
        Comment result = commentDao.findById(commentFromDB.getId()).get();

        assertEquals(commentFromDB, result);
    }

    @Test
    void shouldFindByBook_whenGiveBook() {
        List<Comment> commentsFromDB = commentDao.findByBook(book);

        assertTrue(commentsFromDB.contains(saveComment));
    }

    @Test
    void shouldSaveComment_whenGiveComment() {
        Comment commentFromDB = commentDao.save(commentTolstoy);
        commentFromDB = commentDao.findById(commentFromDB.getId()).get();

        assertEquals(commentFromDB.getText(), commentTolstoy.getText());
        assertEquals(commentFromDB.getBook(), commentTolstoy.getBook());
    }

    @Test
    void shouldDeleteById_whenGiveId() {
        Comment commentFromDB = commentDao.save(commentTolstoy);
        commentDao.deleteById(commentFromDB.getId());

        Optional<Comment> byId = commentDao.findById(commentFromDB.getId());
        assertFalse(byId.isPresent());
    }

    @Test
    void shouldUpdateBook_whenGiveBook() {
        Comment commentFromDB = commentDao.save(commentTolstoy);
        commentFromDB.setText("Update comment");
        commentDao.update(commentFromDB);
        Optional<Comment> updateCommentFromDB = commentDao.findById(commentFromDB.getId());

        assertEquals(commentFromDB.getText(), updateCommentFromDB.get().getText());
    }
}
