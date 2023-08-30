package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@Service
@RequiredArgsConstructor
public class UserInteractionImpl implements UserInteraction {

    private final IOService ioService;

    @Override
    public Book createBook() {
        ioService.printString("Enter name: ");
        String name = ioService.readString();
        Book book = new Book();
        book.setName(name);
        return book;
    }

    @Override
    public Comment createComment() {
        ioService.printString("Enter comment text: ");
        String text = ioService.readString();
        Comment comment = new Comment();
        comment.setText(text);
        return comment;
    }

    @Override
    public Long getId() {
        ioService.printString("Enter id: ");
        return Long.parseLong(ioService.readString());
    }
}
