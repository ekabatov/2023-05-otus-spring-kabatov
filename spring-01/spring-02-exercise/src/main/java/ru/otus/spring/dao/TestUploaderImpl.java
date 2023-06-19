package ru.otus.spring.dao;

import lombok.AllArgsConstructor;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class TestUploaderImpl implements ITestUploader {

    private IOService ioService;

    @Override
    public Test uploadTest() {
        List<String> testRawData = ioService.readData();
        List<Question> questions = testRawData.stream()
                .map(this::parseLine)
                .toList();
        Test test = new Test();
        test.setQuestions(questions);
        return test;
    }

    private Question parseLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        List<String> answers = new ArrayList<>();
        for (int i = 1; i < values.size()-1; i++) {
            answers.add(values.get(i));
        }
        return new Question(values.get(0), answers, values.get(values.size()-1));
    }

}
