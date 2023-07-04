package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.User;
import ru.otus.domain.UserResult;

import java.util.List;

@Service
public class CountUserResultServiceImpl implements CountUserResultService {

    @Override
    public String createResultUserTestString(User user, List<UserResult> answers, Test test) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getLastName())
                .append(" ")
                .append(user.getFirstName())
                .append(" result:")
                .append("\n");
        for (int i = 1; i <= test.getQuestions().size(); i++) {
            Question question = test.getQuestions().get(i - 1);
            String result;
            if (answers.get(i-1).getAnswer().equals(question.getRightAnswer())) {
                result = "True";
            } else {
                result = "False";
            }
            sb.append(i)
                    .append(". ")
                    .append(answers.get(i-1).getAnswer())
                    .append(",")
                    .append(result)
                    .append("\n");
        }
        return sb.toString();
    }
}
