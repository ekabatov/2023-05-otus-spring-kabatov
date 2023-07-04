package ru.otus.service;

import ru.otus.domain.Test;
import ru.otus.domain.UserResult;

import java.util.List;

public interface TestService {

    List<UserResult> testUser(Test test);
}
