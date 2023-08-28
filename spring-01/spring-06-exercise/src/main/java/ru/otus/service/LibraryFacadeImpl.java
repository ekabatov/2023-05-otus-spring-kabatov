package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exception.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LibraryFacadeImpl implements LibraryFacade {

    private final IOService ioService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final CommentService commentService;

    private final UserInteraction userInteraction;

    @Override
    public void createBook() {
        Book book = createBookByUser();
        bookService.save(book);
    }

    @Override
    public void updateBook() {
        ioService.printString("Enter book id: ");
        Long id = userInteraction.getId();
        Book book = createBookByUser();
        Book updateBook = new Book();
        updateBook.setId(id);
        updateBook.setName(book.getName());
        updateBook.setAuthors(book.getAuthors());
        updateBook.setGenres(book.getGenres());
        bookService.update(updateBook);
    }

    @Override
    public void printBook() {
        Long id = userInteraction.getId();
        Optional<Book> book = bookService.findById(id);
        ioService.printString(book.toString());
    }

    @Override
    public void printBooks() {
        ioService.printString("Show all books");
        List<Book> books = bookService.findAll();
        books.forEach(System.out::println);
    }

    @Override
    public void printAuthors() {
        List<Author> authors = authorService.findAll();
        Optional<String> authorsString = authors.stream()
                .map(author -> author.getId() + " - " + author.getName())
                .reduce((s, s2) -> s + "\n" + s2);
        if (authorsString.isPresent()) {
            ioService.printString(authorsString.get());
        } else {
            ioService.printString("Authors is not found");
        }
    }

    @Override
    public void printGenres() {
        List<Genre> genres = genreService.findAll();
        Optional<String> genreString = genres.stream()
                .map(author -> author.getId() + " - " + author.getName())
                .reduce((s, s2) -> s + "\n" + s2);
        if (genreString.isPresent()) {
            ioService.printString(genreString.get());
        } else {
            ioService.printString("Genres is not found");
        }
    }

    @Override
    public void deleteBook() {
        Long id = userInteraction.getId();
        bookService.deleteById(id);
    }

    @Override
    public void createComment() {
        ioService.printString("Enter book id");
        Long id = userInteraction.getId();
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Comment comment = userInteraction.createComment();
            comment.setBook(book.get());
            commentService.save(comment);
        } else {
            throw new EntityNotFoundException("Book with id = " + id + " is not found");
        }
    }

    @Override
    public void printComments() {
        ioService.printString("Show all comments for book");
        ioService.printString("Enter book");
        Long id = userInteraction.getId();
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            List<Comment> comments = commentService.findByBook(book.get());
            comments.forEach(System.out::println);
        } else {
            throw new EntityNotFoundException("Book with id = " + id + " is not found");
        }
    }

    @Override
    public void updateComment() {
        ioService.printString("Enter comment id");
        Long id = userInteraction.getId();
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            Comment updateComment = userInteraction.createComment();
            updateComment.setId(comment.get().getId());
            updateComment.setBook(comment.get().getBook());
            commentService.update(updateComment);
        } else {
            throw new EntityNotFoundException("Comment with id = " + id + " is not found");
        }
    }

    @Override
    public void deleteComment() {
        ioService.printString("Enter comment id");
        Long id = userInteraction.getId();
        commentService.deleteById(id);
    }

    private Book createBookByUser() {
        Book book = userInteraction.createBook();
        book.setGenres(getGenresForBook());
        book.setAuthors(getAuthorsForBook());
        return book;
    }

    private Set<Genre> getGenresForBook() {
        Set<Genre> genres = new HashSet<>();
        printGenres();
        ioService.printString("Choice genre. For exit enter -1!");
        Long id;
        while ((id = userInteraction.getId()) != -1) {
            Optional<Genre> genre = genreService.findById(id);
            Long finalId = id;
            genre.ifPresentOrElse(genres::add, () -> {
                throw new EntityNotFoundException("Genre id = " + finalId + " not found");
            });
        }
        return genres;
    }

    private Set<Author> getAuthorsForBook() {
        Set<Author> authors = new HashSet<>();

        printAuthors();

        ioService.printString("Choice author. For exit enter -1!");
        Long id;
        while ((id = userInteraction.getId()) != -1) {
            Optional<Author> author = authorService.findById(id);
            Long finalId = id;
            author.ifPresentOrElse(authors::add, () -> {
                throw new EntityNotFoundException("Author id = " + finalId + " not found");
            });
        }
        return authors;
    }
}
