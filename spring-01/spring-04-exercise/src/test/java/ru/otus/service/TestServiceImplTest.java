package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Question;
import ru.otus.domain.UserResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private IOService ioService;

    @InjectMocks
    private TestServiceImpl testService;

    @Test
    void should_giveTestResults_whenTestUser() {
        ru.otus.domain.Test test = new ru.otus.domain.Test();
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
        List<UserResult> results = new ArrayList<>();
        results.add(userResult);

        given(ioService.readString())
                .willReturn("b");
        doNothing().when(ioService).printString(isA(String.class));

        assertThat(testService.testUser(test))
                .isEqualTo(results);
    }
}