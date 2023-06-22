package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dao.TestUploader;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class UserTestService implements PrintTestService {

    private TestUploader testUploader;

    @Override
    public void printTest() {
        Test test = testUploader.uploadTest();
        User user = getUser();
        Map<Integer, String> answersUser = testUser(test);
        String resultUser = countResultUser(user, answersUser, test);

        System.out.println(resultUser);
    }

    private User getUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your lastname?");
        String lastName = scanner.next();
        System.out.println("What's your firstname?");
        String firstName = scanner.next();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    private Map<Integer, String> testUser(Test test) {
        System.out.println("Answer some questions:");
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> answers = new HashMap<>();
        for (int i = 1; i <= test.getQuestions().size(); i++) {
            char count = 'a';
            StringBuilder sb = new StringBuilder();
            Question question = test.getQuestions().get(i - 1);
            sb.append(i).append(". ").append(question.getQuestion()).append("\n");
            for (int j = 0; j < question.getAnswer().size(); j++) {
                sb.append(count++).append(") ").append(question.getAnswer().get(j)).append("\n");
            }
            System.out.println(sb);
            System.out.println("Your answer:");
            answers.put(i, scanner.next());
        }
        return answers;
    }

    private String countResultUser(User user, Map<Integer, String> answers, Test test) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getLastName()).append(" ").append(user.getFirstName()).append(" result:").append("\n");
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
        return sb.toString();
    }
}
