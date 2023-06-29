package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class IOServiceImplTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void should_printString_whenGivenString() {
        String data = "Hello, World!";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        IOServiceImpl ioService = new IOServiceImpl(System.in, System.out);
        ioService.printString(data);
        assertThat(data)
                .isEqualTo(outputStreamCaptor.toString().trim());
    }

    @Test
    void should_readString_when() {
        String data = "Hello, World!";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        IOServiceImpl ioService = new IOServiceImpl(System.in, System.out);
        assertThat(ioService.readString())
                .isEqualTo(data);
    }
}