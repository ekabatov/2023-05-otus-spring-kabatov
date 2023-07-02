package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private IOService ioService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void should_CreateUser_When_GiveUserNameAndLastName() {
        User user = new User();
        user.setLastName("Maxim");
        user.setFirstName("Maxim");

        given(ioService.readString())
                .willReturn("Maxim");

        assertThat(userService.createUser())
                .isEqualTo(user);

        verify(ioService, times(2))
                .readString();
    }
}