package ru.otus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class User {

    private String firstName;

    private String lastName;
}
