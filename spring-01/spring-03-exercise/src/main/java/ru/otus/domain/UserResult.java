package ru.otus.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class UserResult {

    private int numberQuestion;

    private String answer;
}
