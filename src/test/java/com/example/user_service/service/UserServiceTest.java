package com.example.user_service.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.services.UserService;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test saveUser method")
    public void testSaveUser() {
        User user = new User();
        user.setName("John Doe");
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);

    }

    @Test
    @DisplayName("Test getUserById method")
    public void testGetUserById_UserExists() {
        User user = new User();
        long userId = 1L;
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.getUserById(userId);
        verify(userRepository, times(1)).findById(userId);
        assert result != null;
        assert result.getId() == userId;
    }

    @Test
    @DisplayName("Test getUserById method when user does not exist")
    void testGetUserById_UserDoesNotExist() {
        long userId = 2L;
        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());
        User result = userService.getUserById(userId);
        assertNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    @DisplayName("Test getUsers method")
    public void testGetUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Smith");

        Pageable pageable = PageRequest.of(0, 10);
        Page<User> page = new PageImpl<>(Arrays.asList(user1, user2), pageable, 2);
        when(userRepository.findAll(pageable)).thenReturn(page);
        Page<User> result = userService.getUsers(pageable);
        verify(userRepository, times(1)).findAll(pageable);
        assert result.getContent().size() == 2;
    }
}
