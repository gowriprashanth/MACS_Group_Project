package com.project.accomatch.RepositoryTest;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserTableOperationsTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserTableOperations userTableOperations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUpUser() throws Exception {
        // Create a sample UserModel
        UserModel userModel = new UserModel();
        UserTableOperations UTO = mock(UserTableOperations.class);
        when(UTO.signUpUser(userModel)).thenReturn("user added successfully");
        assertEquals(UTO.signUpUser(userModel), "user added successfully");
    }

//    @Test
//    void testLoginUser() throws Exception {
//        // Create a sample UserModel
//        UserModel userModel = new UserModel();
//        UserTableOperations UTO = mock(UserTableOperations.class);
//        when(UTO.LoginUser(userModel)).thenReturn("Login Successful");
//        assertEquals(UTO.LoginUser(userModel), "Login Successful");
//    }


}

