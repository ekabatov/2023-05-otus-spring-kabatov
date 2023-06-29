package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }
}
