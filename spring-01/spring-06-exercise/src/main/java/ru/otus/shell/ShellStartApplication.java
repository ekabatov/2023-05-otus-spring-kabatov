package ru.otus.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.LibraryFacade;

@ShellComponent
@AllArgsConstructor
public class ShellStartApplication {

    private LibraryFacade libraryFacade;

    @ShellMethod(value = "Create book", key = {"create book"})
    public void createBook() {
        libraryFacade.createBook();
    }

    @ShellMethod(value = "Print book", key = {"print book"})
    public void printBook() {
        libraryFacade.printBook();
    }

    @ShellMethod(value = "Print book", key = {"print books"})
    public void printBooks() {
        libraryFacade.printBooks();
    }

    @ShellMethod(value = "Edit book", key = {"edit book"})
    public void editBook() {
        libraryFacade.updateBook();
    }

    @ShellMethod(value = "Delete book", key = {"delete book"})
    public void deleteBook() {
        libraryFacade.deleteBook();
    }

    @ShellMethod(value = "Print authors", key = {"authors", "a"})
    public void printAuthors() {
        libraryFacade.printAuthors();
    }

    @ShellMethod(value = "Print genres", key = {"genres", "g"})
    public void printGenres() {
        libraryFacade.printGenres();
    }

    @ShellMethod(value = "Create comment", key = {"create comment", "cc"})
    public void createComment() {
        libraryFacade.createComment();
    }

    @ShellMethod(value = "Print comments", key = {"print comments", "pc"})
    public void printCommentsForBook() {
        libraryFacade.printComments();
    }

    @ShellMethod(value = "Update comment", key = {"update comment", "uc"})
    public void updateComment() {
        libraryFacade.updateComment();
    }

    @ShellMethod(value = "Delete comment", key = {"delete comment", "dc"})
    public void deleteComment() {
        libraryFacade.deleteComment();
    }
}
