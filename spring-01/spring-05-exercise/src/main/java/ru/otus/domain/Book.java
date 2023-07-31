package ru.otus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Data
@ToString
@EqualsAndHashCode
public class Book {
    private Long id;

    private String name;

    private List<Author> authors;

    private List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
