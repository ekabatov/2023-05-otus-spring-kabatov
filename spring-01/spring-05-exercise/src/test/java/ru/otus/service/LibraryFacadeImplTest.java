package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryFacadeImplTest {

    @Mock
    IOService ioService;

    @Mock
    AuthorService authorService;

    @Mock
    GenreService genreService;

    @Mock
    BookService bookService;

    @Mock
    UserInteraction userInteraction;

    @InjectMocks
    LibraryFacadeImpl libraryFacade;

    Genre genre;

    Book book;

    Author author;

    @BeforeEach
    public void setUp() {
        genre = new Genre();
        genre.setId(1L);
        genre.setName("Name");

        author = new Author();
        author.setId(1L);
        author.setName("Name");

        book = new Book();
        book.setId(1L);
        book.setName("Name");
    }

    @Test
    public void shouldCreateBook() {
        given(userInteraction.createBook()).willReturn(book);
        doNothing().when(bookService).save(book);
        doNothing().when(ioService).printString(isA(String.class));
        when(userInteraction.getId())
                .thenReturn(1L)
                .thenReturn(-1L)
                .thenReturn(1L)
                .thenReturn(-1L);
        given(genreService.findById(1L)).willReturn(Optional.ofNullable(genre));
        given(authorService.findById(1L)).willReturn(Optional.ofNullable(author));

        libraryFacade.createBook();

        verify(userInteraction, times(1))
                .createBook();
        verify(userInteraction, times(4))
                .getId();
        verify(bookService, times(1))
                .save(book);
        verify(genreService, times(1))
                .findById(1L);
        verify(authorService, times(1))
                .findById(1L);
    }

    @Test
    public void shouldUpdateBook() {
        given(userInteraction.createBook()).willReturn(book);
        doNothing().when(bookService).update(book);
        doNothing().when(ioService).printString(isA(String.class));
        when(userInteraction.getId())
                .thenReturn(1L)
                .thenReturn(1L)
                .thenReturn(-1L)
                .thenReturn(1L)
                .thenReturn(-1L);
        given(genreService.findById(1L)).willReturn(Optional.ofNullable(genre));
        given(authorService.findById(1L)).willReturn(Optional.ofNullable(author));

        libraryFacade.updateBook();

        verify(userInteraction, times(1))
                .createBook();
        verify(userInteraction, times(5))
                .getId();
        verify(bookService, times(1))
                .update(book);
        verify(genreService, times(1))
                .findById(1L);
        verify(authorService, times(1))
                .findById(1L);
    }

    @Test
    public void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(1L);
        when(userInteraction.getId())
                .thenReturn(1L);

        libraryFacade.deleteBook();

        verify(userInteraction, times(1))
                .getId();
        verify(bookService, times(1))
                .deleteById(1L);
    }
}