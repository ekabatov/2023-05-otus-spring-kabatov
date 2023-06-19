package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Question {

    private String question;

    private List<String> answers;

    private String rightAnswer;
}
