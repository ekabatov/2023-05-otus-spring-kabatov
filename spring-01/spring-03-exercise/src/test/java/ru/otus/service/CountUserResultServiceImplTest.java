package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.domain.UserResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CountUserResultServiceImplTest {

    User user;

    ru.otus.domain.Test test;

    List<UserResult> results;

    CountUserResultServiceImpl countUserResultService;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setLastName("Maxim");
        user.setFirstName("Maxim");

        test = new ru.otus.domain.Test();
        List<String> answers = new ArrayList<>();
        answers.add("method");
        answers.add("native");
        answers.add("subclasses");
        answers.add("reference");
        Question question = new Question("Which is a reserved word in the Java programming language?", answers, "b");
        test.setQuestions(new ArrayList<>());
        test.getQuestions().add(question);

        UserResult userResult = UserResult.builder()
                .numberQuestion(1)
                .answer("b")
                .build();
        results = new ArrayList<>();
        results.add(userResult);

        countUserResultService = new CountUserResultServiceImpl();
    }

    @Test
    void should_createResultUserTestString_whenUserMakeTest() {
        String result = "Maxim Maxim result:\n" +
                "1. b,True\n";

        assertThat(countUserResultService.createResultUserTestString(user, results, test))
                .isEqualTo(result);
    }
}