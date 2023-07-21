package com.project.accomatch.ServiceTest;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.Implementation.UserTableOperations;
import com.project.accomatch.Service.Implementation.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("Status", "Success");
        when(userTableOperations.LoginUser(userModel)).thenReturn(returnMap);

        // Call the method under test
        Map<String,String> result = userService.Login(userModel);

        // Verify the expected behavior
        verify(userTableOperations, times(1)).LoginUser(userModel);
        assertEquals("Success", result.get("Status"));

    }

    @Test
    void testLoginFailure() {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        // Set up any necessary mocks
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("Status", "Failure");
        when(userTableOperations.LoginUser(userModel)).thenReturn(returnMap);

        // Call the method under test
        Map<String,String> result = userService.Login(userModel);

        // Verify the expected behavior
        verify(userTableOperations, times(1)).LoginUser(userModel);
        assertEquals("Failure", result.get("Status"));
    }

    @Test
    void forgotPassword_shouldReturnResultFromUserTableOperations() {
        UserModel userModel = new UserModel();
        when(userTableOperations.ForgotPassword(any(UserModel.class))).thenReturn("Password Updated");
        String Res = userService.ForgotPassword(userModel);
        assertEquals("Password Updated", Res);
    }

    @Test
    void checkEmailID_shouldReturnResultFromUserTableOperations() {
        UserModel userModel = new UserModel();
        when(userTableOperations.ForgotPassword(any(UserModel.class))).thenReturn("Password Updated");
        String Res = userService.ForgotPassword(userModel);
        assertEquals("Password Updated", Res);
    }
}

