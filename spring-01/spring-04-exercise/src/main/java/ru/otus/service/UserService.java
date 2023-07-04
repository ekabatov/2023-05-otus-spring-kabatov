package ru.otus.service;

import ru.otus.domain.User;

public interface UserService {
    User createUser();

    User createUser(String firstName, String lastName);
}
