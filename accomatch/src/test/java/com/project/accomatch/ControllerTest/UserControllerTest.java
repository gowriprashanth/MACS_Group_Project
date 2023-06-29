package com.project.accomatch.ControllerTest;

import com.project.accomatch.Controller.UserController;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    UserService userService;
    @Mock
    MailSenderClass mailSenderClass;

    @InjectMocks
    UserController usercontroller;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUpSuccess() {
        when(userService.SignUp(any(UserModel.class))).thenReturn("user added successfully");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.signUp(userModel), "user added successfully");
        verify(userService, times(1)).SignUp(userModel);
    }

    @Test
    public void testSignUpFailure() {
        when(userService.SignUp(any(UserModel.class))).thenReturn("Error occurred");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.signUp(userModel), "Error occurred");
        verify(userService, times(1)).SignUp(userModel);
    }
    @Test
    public void testLoginSuccess() {
        when(userService.Login(any(UserModel.class))).thenReturn("Login Successful");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.login(userModel), "Login Successful");
        verify(userService, times(1)).Login(userModel);
    }

    @Test
    public void testLoginFailure() {
        when(userService.Login(any(UserModel.class))).thenReturn("User not found");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.login(userModel), "User not found");
        verify(userService, times(1)).Login(userModel);
    }


    @Test
    void forgotPassword_whenEmailExists_shouldReturnMailSent() {
        UserModel userModel = mock(UserModel.class);
        when(userModel.getEmail()).thenReturn("abcd");
        doNothing().when(mailSenderClass).sendEmail(anyString(), anyString(), anyString());
        when(userService.CheckEmailID(anyString())).thenReturn("ID exists");
        assertEquals(usercontroller.forgotPassword(userModel), "Mail Sent");
    }

    @Test
    void forgotPassword_whenEmailDoesNotExist_shouldReturnErrorMessage() throws Exception {
        UserModel userModel = mock(UserModel.class);
        when(userModel.getEmail()).thenReturn("abcd");
        doNothing().when(mailSenderClass).sendEmail(anyString(), anyString(), anyString());
        when(userService.CheckEmailID(anyString())).thenReturn("ID doesnt Exist");
        assertEquals(usercontroller.forgotPassword(userModel), "ID doesnt Exist");
    }
    @Test
    void updatePassword_whenCalled_shouldReturnResultFromService() throws Exception {
        when(userService.ForgotPassword(any(UserModel.class))).thenReturn("Password Updated");
        UserModel userModel = new UserModel();
        assertEquals(usercontroller.updatePassword(userModel), "Password Updated");
        verify(userService, times(1)).ForgotPassword(userModel);
    }
}

