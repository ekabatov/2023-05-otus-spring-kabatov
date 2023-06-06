package ru.otus.spring.dao;

import org.springframework.util.ResourceUtils;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvTestUploaderImpl implements ITestUploader {

    private final String url;

    public CsvTestUploaderImpl(String url) {
        this.url = url;
    }

    @Override
    public Test uploadTest() {
        List<Question> questions = new ArrayList<>();
        try (Scanner scanner = new Scanner(ResourceUtils.getFile(url))) {
            while (scanner.hasNextLine()) {
                questions.add(parseLine(scanner.nextLine()));
            }
            Test test = new Test();
            test.setQuestions(questions);
            return test;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Question parseLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        Question question = new Question();
        question.setQuestion(values.get(0));
        question.setAnswers(new ArrayList<>());
        for (int i = 1; i < values.size(); i++) {
            question.getAnswers().add(values.get(i));
        }
        return question;
    }

}
