package ru.otus.spring.service;

import ru.otus.spring.dao.ITestUploader;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Test;

public class PrintTestServiceImpl implements IPrintTestService {

    private ITestUploader testUploader;

    public PrintTestServiceImpl(ITestUploader testUploader) {
        this.testUploader = testUploader;
    }

    @Override
    public void printTest() {
        Test test = testUploader.uploadTest();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= test.getQuestions().size(); i++) {
            Question question = test.getQuestions().get(i - 1);
            sb.append(i).append(". ").append(question.getQuestion()).append("\n");
            for (int j = 0; j < question.getAnswers().size(); j++) {
                sb.append("- ").append(question.getAnswers().get(j)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
