package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Question {

    private String question;

    private List<String> answer;

    private String rightAnswer;
}
