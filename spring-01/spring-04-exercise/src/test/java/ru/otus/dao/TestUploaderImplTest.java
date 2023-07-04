package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TestUploaderImplTest {

    ru.otus.domain.Test test;

    @Autowired
    private TestUploaderImpl testUploader;

    @BeforeEach
    public void setUp() {
        test = new ru.otus.domain.Test();
        List<String> answers1 = new ArrayList<>();
        List<String> answers2 = new ArrayList<>();
        List<String> answers3 = new ArrayList<>();
        List<String> answers4 = new ArrayList<>();
        List<String> answers5 = new ArrayList<>();
        answers1.add("method");
        answers1.add("method");
        answers1.add("method");
        answers1.add("method");

        answers2.add("method");
        answers2.add("method");
        answers2.add("method");
        answers2.add("method");

        answers3.add("method");
        answers3.add("method");
        answers3.add("method");
        answers3.add("method");

        answers4.add("method");
        answers4.add("method");
        answers4.add("method");
        answers4.add("method");

        answers5.add("method");
        answers5.add("method");
        answers5.add("method");
        answers5.add("method");
        Question question1 = new Question("Which is a reserved word in the Java programming language?", answers1, "b");
        Question question2 = new Question("Which is a valid keyword in java?", answers2, "a");
        Question question3 = new Question("Which class does not override the equals() and hashCode() methods inheriting them directly from class Object?", answers3, "c");
        Question question4 = new Question("Which collection class allows you to grow or shrink its size and provides indexed access to its elements but whose methods are not synchronized?", answers4, "d");
        Question question5 = new Question("Which interface does java.util.Hashtable implement?", answers5, "a");
        test.setQuestions(new ArrayList<>());
        test.getQuestions().add(question1);
        test.getQuestions().add(question2);
        test.getQuestions().add(question3);
        test.getQuestions().add(question4);
        test.getQuestions().add(question5);
    }

    @Test
    void should_loadTest_whenExistTest() {
        assertThat(testUploader.loadTest())
                .isEqualTo(test);
    }
}