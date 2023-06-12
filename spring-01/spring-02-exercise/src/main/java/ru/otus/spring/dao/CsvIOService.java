package ru.otus.spring.dao;

import lombok.AllArgsConstructor;
import org.springframework.util.ResourceUtils;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class CsvIOService implements IOService{

    private final String url;

    @Override
    public List<String> readData() {
        List<String> csvLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(ResourceUtils.getFile(url))) {
            while (scanner.hasNextLine()) {
                csvLines.add(scanner.nextLine());
            }
            return csvLines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
