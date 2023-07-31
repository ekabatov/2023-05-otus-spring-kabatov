package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exception.AuthorNotFoundException;
import ru.otus.exception.GenreNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LibraryFacadeImpl implements LibraryFacade {
    private final IOService ioService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

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

    private Book createBookByUser() {
        Book book = userInteraction.createBook();
        book.setGenres(getGenresForBook());
        book.setAuthors(getAuthorsForBook());

        return book;
    }

    private List<Genre> getGenresForBook() {
        List<Genre> genres = new ArrayList<>();
        printGenres();
        ioService.printString("Choice genre. For exit enter -1!");
        Long id;
        while ((id = userInteraction.getId()) != -1) {
            Optional<Genre> genre = genreService.findById(id);
            Long finalId = id;
            genre.ifPresentOrElse(genres::add, () -> {
                throw new GenreNotFoundException("Genre id = " + finalId + " not found");
            });
        }
        return genres;
    }

    private List<Author> getAuthorsForBook() {
        List<Author> authors = new ArrayList<>();

        printAuthors();

        ioService.printString("Choice author. For exit enter -1!");
        Long id;
        while ((id = userInteraction.getId()) != -1) {
            Optional<Author> author = authorService.findById(id);
            Long finalId = id;
            author.ifPresentOrElse(authors::add, () -> {
                throw new AuthorNotFoundException("Author id = " + finalId + " not found");
            });
        }
        return authors;
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
}
