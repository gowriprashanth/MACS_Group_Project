package com.project.accomatch.ServiceTest;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperations;
import com.project.accomatch.Service.Implementation.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplementationTest {

    @Mock
    private UserTableOperations userTableOperations;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp() {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        // Set up any necessary mocks
        when(userTableOperations.signUpUser(userModel)).thenReturn("user added successfully");

        // Call the method under test
        String result = userService.SignUp(userModel);

        // Verify the expected behavior
        verify(userTableOperations, times(1)).signUpUser(userModel);
        assertEquals("user added successfully", result);
    }

    @Test
    void testSignUpFailure() {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        // Set up any necessary mocks
        when(userTableOperations.signUpUser(userModel)).thenReturn("Error Occurred");

        // Call the method under test
        String result = userService.SignUp(userModel);

        // Verify the expected behavior
        verify(userTableOperations, times(1)).signUpUser(userModel);
        assertEquals("Error Occurred", result);
    }

    @Test
    void testLogin() {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        // Set up any necessary mocks
        when(userTableOperations.LoginUser(userModel)).thenReturn("Login Successful");

        // Call the method under test
        String result = userService.Login(userModel);

        // Verify the expected behavior
        verify(userTableOperations, times(1)).LoginUser(userModel);
        assertEquals("Login Successful", result);

    }

    @Test
    void testLoginFailure() {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        // Set up any necessary mocks
        when(userTableOperations.LoginUser(userModel)).thenReturn("Error occurred");

        // Call the method under test
        String result = userService.Login(userModel);

        // Verify the expected behavior
        verify(userTableOperations, times(1)).LoginUser(userModel);
        assertEquals("Error occurred", result);
    }
}

