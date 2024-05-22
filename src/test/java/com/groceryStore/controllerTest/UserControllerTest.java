package com.groceryStore.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groceteria.controller.UserController;
import com.groceteria.entity.User;
import com.groceteria.repository.UserRepository;
import com.groceteria.service.UserService;

public class UserControllerTest {

    private UserService userService;
    private UserRepository userRepository;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userRepository = mock(UserRepository.class);
        userController = new UserController(userService);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setFirstName("John");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.saveUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setEmailId("test@example.com");
        when(userService.loginUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.loginUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUserId(1);
        when(userService.updateUser(any(User.class), anyInt())).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.updateUser(1, user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        userList.add(user1);
        userList.add(user2);
        when(userService.getAllUser()).thenReturn(userList);

        List<User> result = userController.getAllUser();

        assertEquals(userList.size(), result.size());
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setEmailId("test@example.com");
        when(userRepository.findByEmailId(anyString())).thenReturn(Optional.of(user));

        User result = userController.getUserByEmail(user);

        assertEquals(user, result);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(1);
        when(userService.getUserById(anyInt())).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUserById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<Boolean> responseEntity = userController.deleteUser(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());
    }

    @Test
    void testChangeUserPassword() {
        User user = new User();
        user.setUserId(1);
        user.setPassword("newpassword");
        when(userService.getUserById(anyInt())).thenReturn(user);

        User result = userController.changeUserPassword(1, "newpassword");

        assertEquals("newpassword", result.getPassword());
    }
}
