package ru.otus.commandlinerunners;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.otus.service.PrintService;

@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
@Component
@AllArgsConstructor
public class StartApplication implements CommandLineRunner {
    private PrintService printTestService;

    @Override
    public void run(String... args) throws Exception {
        printTestService.askTest();
    }
}
