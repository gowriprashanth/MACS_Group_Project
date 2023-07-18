package com.project.accomatch.ControllerTest;

import com.project.accomatch.Controller.AdminController;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.AdminInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AdminControllerTest {

    @Mock
    AdminInterface adminInterface;
    @InjectMocks
    AdminController adminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verifyOneTestSuccess(){
        Posts posts = mock(Posts.class);
        when(adminInterface.VerifyOneAd(posts)).thenReturn("Success");
        assertEquals("Success", adminController.verifySingleAd(posts));
        verify(adminInterface, times(1)).VerifyOneAd(posts);
    }

    @Test
    public void verifyOneTestFailure(){
        Posts posts = mock(Posts.class);
        when(adminInterface.VerifyOneAd(posts)).thenReturn("Fail");
        assertEquals("Fail", adminController.verifySingleAd(posts));
        verify(adminInterface, times(1)).VerifyOneAd(posts);
    }

    @Test
    public void verifyAllTestSuccess(){
        Posts posts = mock(Posts.class);
        when(adminInterface.VerifyAllAd(posts)).thenReturn("Success");
        assertEquals("Success", adminController.verifyAllAd(posts));
        verify(adminInterface, times(1)).VerifyAllAd(posts);
    }

    @Test
    public void verifyAllTestFailure(){
        Posts posts = mock(Posts.class);
        when(adminInterface.VerifyAllAd(posts)).thenReturn("Fail");
        assertEquals("Fail", adminController.verifyAllAd(posts));
        verify(adminInterface, times(1)).VerifyAllAd(posts);
    }
}
