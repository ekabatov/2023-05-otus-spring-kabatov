package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.TestUploader;
import ru.otus.domain.Test;
import ru.otus.domain.User;
import ru.otus.domain.UserResult;

import java.util.List;

@Service
@AllArgsConstructor
public class PrintServiceImpl implements PrintService {

    private UserService userService;

    private TestService testService;

    private CountUserResultService countUserResultService;

    private TestUploader testUploader;

    private IOService ioService;

    @Override
    public void askTest() {
        Test test = testUploader.loadTest();
        User user = userService.createUser();
        List<UserResult> userResults = testService.testUser(test);
        String resultUserTestString = countUserResultService.createResultUserTestString(user, userResults, test);
        ioService.printString(resultUserTestString);
    }
}
