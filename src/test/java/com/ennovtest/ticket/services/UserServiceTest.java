package com.ennovtest.ticket.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Repositories.UserRepository;
import com.ennovtest.ticket.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setUsername("userA");
        user.setEmail("usera@mail.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User userCreated = userService.save(user);

        assertNotNull(userCreated);
        assertEquals("userA", userCreated.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}
