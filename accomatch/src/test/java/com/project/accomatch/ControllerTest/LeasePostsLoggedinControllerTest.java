package com.project.accomatch.ControllerTest;


import com.project.accomatch.Controller.LeasePostsLoggedinController;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.LeasePostsLoggedinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LeasePostsLoggedinControllerTest {

    private LeasePostsLoggedinService applicantService;
    private LeasePostsLoggedinController leasePostsLoggedinController;

    @BeforeEach
    void setUp() {
        applicantService = mock(LeasePostsLoggedinService.class);
        leasePostsLoggedinController = new LeasePostsLoggedinController();
        leasePostsLoggedinController.applicantService = applicantService;
    }

    @Test
    void testGetListOfLoggedinPosts() {
        List<Posts> postsList = new ArrayList<>();
        postsList.add(new Posts());
        postsList.add(new Posts());
        when(applicantService.getListOfLoggedinApplicants(anyInt())).thenReturn(postsList);
        List<Posts> result = leasePostsLoggedinController.getListOfLoggedinPosts(1);
        verify(applicantService, times(1)).getListOfLoggedinApplicants(1);
        assertEquals(postsList, result);
    }
}
