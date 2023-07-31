package ru.otus.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class Genre {
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Genre genre = (Genre) o;
        return id != null && Objects.equals(id, genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
