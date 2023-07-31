package ru.otus.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class Author {
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Author author = (Author) o;
        return id != null && Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
