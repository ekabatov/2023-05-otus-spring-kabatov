package ru.otus.service;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface UserInteraction {

    Book createBook();

    Comment createComment();

    Long getId();
}
