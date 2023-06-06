package ru.otus.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private String question;

    private List<String> answers;
}
