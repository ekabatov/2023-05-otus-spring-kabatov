package ru.otus.service;

import ru.otus.domain.Test;
import ru.otus.domain.User;
import ru.otus.domain.UserResult;

import java.util.List;

public interface CountUserResultService {

    String createResultUserTestString(User user, List<UserResult> answers, Test test);
}
