package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.TestUploader;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.domain.UserResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private TestService testService;

    @Mock
    private CountUserResultService countUserResultService;

    @Mock
    private TestUploader testUploader;

    @Mock
    private IOService ioService;

    @InjectMocks
    private PrintServiceImpl printService;

    User user;

    ru.otus.domain.Test test;

    List<UserResult> results;

    String resultUserTestString;

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

        resultUserTestString = "Maxim Maxim result:\n" +
                "1. b,True\n";
    }

    @Test
    void should_askTest_whenGiveTestAndUser() {
        given(testUploader.loadTest())
                .willReturn(test);
        given(userService.createUser())
                .willReturn(user);
        given(testService.testUser(test))
                .willReturn(results);
        given(countUserResultService.createResultUserTestString(user, results, test))
                .willReturn(resultUserTestString);
        doNothing().when(ioService).printString(isA(String.class));
        printService.askTest();
        verify(testUploader, times(1))
                .loadTest();
        verify(userService, times(1))
                .createUser();
        verify(testService, times(1))
                .testUser(test);
        verify(countUserResultService, times(1))
                .createResultUserTestString(user, results, test);
        verify(ioService, times(1))
                .printString(isA(String.class));
    }
}