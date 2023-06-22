package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.config.AppProps;
import ru.otus.domain.Question;
import ru.otus.domain.Test;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class TestUploaderImpl implements TestUploader {

    private MessageSource messageSource;

    private AppProps props;

    @Override
    public Test uploadTest() {
        Test test = new Test();
        test.setQuestions(new ArrayList<>());
        for (int i = 0; i < 5; i++) {
            String question = readQuestion(i);
            List<String> answers = readAnswers(i);
            String rightAnswer = readRightAnswer(i);
            test.getQuestions().add(new Question(question, answers, rightAnswer));
        }
        return test;
    }

    private String readQuestion(int index) {
        return messageSource.getMessage("test.questions[" + index + "].question", null, props.getLocale());
    }

    private List<String> readAnswers(int index) {
        List<String> answers = new ArrayList<>();
        answers.add(messageSource.getMessage("test.questions[" + index + "].answer[0]", null, props.getLocale()));
        answers.add(messageSource.getMessage("test.questions[" + index + "].answer[1]", null, props.getLocale()));
        answers.add(messageSource.getMessage("test.questions[" + index + "].answer[2]", null, props.getLocale()));
        answers.add(messageSource.getMessage("test.questions[" + index + "].answer[3]", null, props.getLocale()));
        return answers;
    }

    private String readRightAnswer(int index) {
        return messageSource.getMessage("test.questions[" + index + "].right-answer", null, props.getLocale());
    }

}
