package ru.otus;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.config.AppProps;
import ru.otus.service.PrintTestService;

@SpringBootApplication
@AllArgsConstructor
@EnableConfigurationProperties(AppProps.class)
public class Main implements CommandLineRunner {

    private PrintTestService printTestService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        printTestService.printTest();
    }
}
