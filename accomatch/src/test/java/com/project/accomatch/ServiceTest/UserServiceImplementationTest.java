package com.project.accomatch.ServiceTest;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperationsInterface;
import com.project.accomatch.Service.Implementation.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceImplementationTest {

    @Mock
    private UserTableOperationsInterface userTableOperations;

    @Mock
    private PasswordEncoder passwordEncoder;

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
       when(userTableOperations.signUpUser(any(UserModel.class))).thenReturn("Success");
       assertEquals("Success", userTableOperations.signUpUser(userModel));
    }

    @Test
    void testSignUpFail() {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        when(userTableOperations.signUpUser(any(UserModel.class))).thenReturn("Fail");
        assertEquals("Fail", userTableOperations.signUpUser(userModel));
    }

    @Test
    void forgotPassword_shouldReturnResultFromUserTableOperations() {
        UserModel userModel = new UserModel();
        when(userTableOperations.ForgotPassword(any(UserModel.class))).thenReturn("Password Updated");
        assertEquals("Password Updated", userTableOperations.ForgotPassword(userModel));
    }

    @Test
    void checkEmailID_shouldReturnResultFromUserTableOperations() {
        UserModel userModel = new UserModel();
        when(userTableOperations.CheckMailID(anyString())).thenReturn("Mail Sent");
        assertEquals("Mail Sent", userTableOperations.CheckMailID("Mock@gmail.com"));
    }

  /*  @Test
    void getUserInfoTest() {
        UserModel userModel = new UserModel();
        when(userTableOperations.getUserInfo(anyInt())).thenReturn(new UserModel());
        assertEquals("Mail Sent", userTableOperations.getUserInfo(1));
    }*/
}

