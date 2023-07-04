package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.User;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private IOService ioService;

    @Override
    public User createUser() {
        ioService.printString("What's your lastname?");
        String lastName = ioService.readString();
        ioService.printString("What's your firstname?");
        String firstName = ioService.readString();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    @Override
    public User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
