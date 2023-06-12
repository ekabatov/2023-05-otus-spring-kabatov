package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.spring.dao.ITestUploader;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public class UserTestService implements IPrintTestService {

    private ITestUploader testUploader;

    @Override
    public void printTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your lastname?");
        String lastName = scanner.next();
        System.out.println("What's your firstname?");
        String firstName = scanner.next();
        System.out.println("Answer some questions:");
        Test test = testUploader.uploadTest();

        Map<Integer, String> answers = new HashMap<>();
        for (int i = 1; i <= test.getQuestions().size(); i++) {
            char count = 'a';
            StringBuilder sb = new StringBuilder();
            Question question = test.getQuestions().get(i - 1);
            sb.append(i).append(". ").append(question.getQuestion()).append("\n");
            for (int j = 0; j < question.getAnswers().size(); j++) {
                sb.append(count++).append(") ").append(question.getAnswers().get(j)).append("\n");
            }
            System.out.println(sb);
            System.out.println("Your answer:");
            answers.put(i, scanner.next());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(lastName).append(" ").append(firstName).append(" result:").append("\n");
        for (int i = 1; i <= test.getQuestions().size(); i++) {

            Question question = test.getQuestions().get(i - 1);
            String result;
            if (answers.get(i).equals(question.getRightAnswer())) {
                result = "True";
            } else {
                result = "False";
            }
            sb.append(i).append(". ").append(answers.get(i)).append(",").append(result).append("\n");
        }
        System.out.println(sb);

        scanner.close();
    }
}
