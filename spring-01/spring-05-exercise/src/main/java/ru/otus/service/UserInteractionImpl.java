package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;

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
    public Long getId() {
        ioService.printString("Enter id: ");
        return Long.parseLong(ioService.readString());
    }
}
