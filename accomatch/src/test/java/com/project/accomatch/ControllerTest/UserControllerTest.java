package com.project.accomatch.ControllerTest;

import com.project.accomatch.Controller.UserController;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    UserService userService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUpSuccess() {
        UserController usercontroller = new UserController();
        usercontroller.userservice = userService;
        when(userService.SignUp(any(UserModel.class))).thenReturn("user added successfully");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.signUp(userModel), "user added successfully");
        verify(userService, times(1)).SignUp(userModel);
    }

    @Test
    public void testSignUpFailure() {
        UserController usercontroller = new UserController();
        usercontroller.userservice = userService;
        when(userService.SignUp(any(UserModel.class))).thenReturn("Error occurred");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.signUp(userModel), "Error occurred");
        verify(userService, times(1)).SignUp(userModel);
    }
    @Test
    public void testLoginSuccess() {
        UserController userController = new UserController();
        userController.userservice = userService;
        when(userService.Login(any(UserModel.class))).thenReturn("Login Successful");
        UserModel userModel = new UserModel();
        assertEquals(userController.login(userModel), "Login Successful");
        verify(userService, times(1)).Login(userModel);
    }

    @Test
    public void testLoginFailure() {
        UserController userController = new UserController();
        userController.userservice = userService;
        when(userService.Login(any(UserModel.class))).thenReturn("User not found");
        UserModel userModel = new UserModel();
        assertEquals(userController.login(userModel), "User not found");
        verify(userService, times(1)).Login(userModel);
    }

}

