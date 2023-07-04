package ru.otus.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.dao.TestUploader;
import ru.otus.domain.Test;
import ru.otus.domain.User;
import ru.otus.domain.UserResult;
import ru.otus.service.CountUserResultService;
import ru.otus.service.IOService;
import ru.otus.service.TestService;
import ru.otus.service.UserService;

import java.util.List;

@ShellComponent
public class ShellStartApplication {

    private User user;

    private Test test;

    private List<UserResult> userResults;

    private final TestService testService;

    private final CountUserResultService countUserResultService;

    private final TestUploader testUploader;

    private final IOService ioService;

    private final UserService userService;

    public ShellStartApplication(TestService testService,
                                 CountUserResultService countUserResultService,
                                 TestUploader testUploader,
                                 IOService ioService,
                                 UserService userService) {
        this.testService = testService;
        this.countUserResultService = countUserResultService;
        this.testUploader = testUploader;
        this.ioService = ioService;
        this.userService = userService;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(String firstName, String lastName) {
        user = userService.createUser(firstName, lastName);
        return "Hello, " + user.getFirstName() + " " + user.getLastName();
    }

    @ShellMethod(value = "Test command", key = {"t", "test"})
    @ShellMethodAvailability(value = "isTestAvailable")
    public void testUser() {
        test = testUploader.loadTest();
        userResults = testService.testUser(test);
    }

    @ShellMethod(value = "Publish command", key = {"p", "pub", "publish"})
    @ShellMethodAvailability(value = "isPublishCommandAvailable")
    public void publishEvent() {
        String resultUserTestString = countUserResultService.createResultUserTestString(user, userResults, test);
        ioService.printString(resultUserTestString);
    }

    private Availability isTestAvailable() {
        return user == null ? Availability.unavailable("Login first") : Availability.available();
    }

    private Availability isPublishCommandAvailable() {
        return userResults == null ? Availability.unavailable("Make test first") : Availability.available();
    }

}
