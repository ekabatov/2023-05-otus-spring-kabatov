package ru.otus.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final Scanner scanner;

    private final PrintStream outputStream;

    public IOServiceImpl(InputStream inputStream, PrintStream outputStream) {
        this.outputStream = outputStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void printString(String s) {
        outputStream.println(s);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
