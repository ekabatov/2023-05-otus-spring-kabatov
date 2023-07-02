package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.UserResult;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private IOService ioService;

    public List<UserResult> testUser(Test test) {
        ioService.printString("Answer some questions:");
        List<UserResult> answers = new ArrayList<>();
        for (int i = 1; i <= test.getQuestions().size(); i++) {
            char count = 'a';
            StringBuilder sb = new StringBuilder();
            Question question = test.getQuestions().get(i - 1);
            sb.append(i).append(". ").append(question.getQuestion()).append("\n");
            for (int j = 0; j < question.getAnswer().size(); j++) {
                sb.append(count++).append(") ").append(question.getAnswer().get(j)).append("\n");
            }
            ioService.printString(sb.toString());
            ioService.printString("Your answer:");
            UserResult userResult = UserResult.builder()
                    .numberQuestion(i)
                    .answer(ioService.readString()).build();

            answers.add(userResult);
        }
        return answers;
    }
}
