package com.project.accomatch.ControllerTest;

import com.project.accomatch.Controller.AdminController;
import com.project.accomatch.Exception.InvalidPostStatusException;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.AdminInterface;
import com.project.accomatch.Service.LeaseHolderDashboardInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AdminControllerTest {

    @Mock
    private LeaseHolderDashboardInterface dashboardService;
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

    @Test
    void testGetListOfPosts_Success() {
        // Arrange
        List<Posts> expectedPostsList = Arrays.asList(new Posts(/* post parameters */));
        when(dashboardService.getListOfPosts()).thenReturn(expectedPostsList);

        // Act
        List<Posts> result = adminController.getListOfPosts();

        // Assert
        assertEquals(expectedPostsList, result);
        verify(dashboardService, times(1)).getListOfPosts();
    }

    @Test
    void testGetListOfPostsByStatus_Success() {
        // Arrange
        int status = 1;
        HashMap<String, String> map = new HashMap<>();
        map.put("status", Integer.toString(status));
        List<Posts> expectedPostsList = Arrays.asList(new Posts(/* post parameters */));
        when(dashboardService.getListOfPostsByStatus(status)).thenReturn(expectedPostsList);

        // Act
        List<Posts> result = adminController.getListOfPostsByStatus(map);

        // Assert
        assertEquals(expectedPostsList, result);
        verify(dashboardService, times(1)).getListOfPostsByStatus(status);
    }

    @Test
    void testGetListOfPostsByStatus_InvalidPostStatusException() {
        // Arrange
        String invalidStatus = "invalid_status";
        HashMap<String, String> map = new HashMap<>();
        map.put("status", invalidStatus);

        // Act & Assert
        assertThrows(InvalidPostStatusException.class, () -> adminController.getListOfPostsByStatus(map));
        verifyNoInteractions(dashboardService);
    }
}
