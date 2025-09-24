package com.spring.identity_service;

import com.spring.identity_service.entity.User;
import com.spring.identity_service.repository.UserRepository;
import com.spring.identity_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsersForCreateOrg() {
        // Arrange
        User activeUser1 = new User();
        activeUser1.setId("1");
        activeUser1.setUsername("activeUser1");
        activeUser1.setActivationDate(LocalDate.now().minusDays(10));
        activeUser1.setExpirationDate(LocalDate.now().plusDays(10));

        User activeUser2 = new User();
        activeUser2.setId("2");
        activeUser2.setUsername("activeUser2");
        activeUser2.setActivationDate(null); // null activation date should be allowed
        activeUser2.setExpirationDate(LocalDate.now().plusDays(5));

        List<User> expectedUsers = Arrays.asList(activeUser1, activeUser2);
        
        when(userRepository.getUsersForCreateOrg(any(LocalDate.class))).thenReturn(expectedUsers);

        // Act
        List<User> result = userService.getUsersForCreateOrg();

        // Assert
        assertEquals(2, result.size());
        assertEquals("activeUser1", result.get(0).getUsername());
        assertEquals("activeUser2", result.get(1).getUsername());
    }
}