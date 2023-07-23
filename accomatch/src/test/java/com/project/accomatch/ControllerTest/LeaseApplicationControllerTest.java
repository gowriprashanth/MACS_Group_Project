package com.project.accomatch.ControllerTest;

import com.project.accomatch.Controller.LeaseApplicationController;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Service.LeaseApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LeaseApplicationControllerTest {

    private LeaseApplicationService applicantService;
    private LeaseApplicationController leaseApplicationController;

    @BeforeEach
    void setUp() {
        applicantService = mock(LeaseApplicationService.class);
        leaseApplicationController = new LeaseApplicationController();
        leaseApplicationController.applicantService = applicantService;
    }

    @Test
    void testGetListOfPosts() {
        List<Applicant> applicantList = new ArrayList<>();
        applicantList.add(new Applicant(2,"dhrumil","dhrumil.gosaliya@gmail.com",25,"male","1234457894"));
        when(applicantService.getListOfApplicants(anyInt())).thenReturn(applicantList);
        List<Applicant> result = leaseApplicationController.getListOfPosts(1);
        verify(applicantService, times(1)).getListOfApplicants(1);
        assertEquals(applicantList, result);
    }

    @Test
    void testGetListOfPostsWithInvalidApplicationId() {
        int invalidApplicationId = -1;
        assertThrows(InvalidInputException.class,
                () -> leaseApplicationController.getListOfPosts(invalidApplicationId));
    }
}
