/**
 * Controller class for the leaseholder dashboard.
 * Author: Ramandeep Kaur
 * @author Ramandeep Kaur
 */
package com.project.accomatch.Controller;

import com.project.accomatch.Exception.DataAccessException;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Model.PostDetails;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.Implementation.LeaseHolderDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/dashboard")
public class LeaseHolderDashboardController {
    @Autowired
    private LeaseHolderDashboardService dashboardService;

    /**
     * Retrieves the list of posts.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @GetMapping("/get/list/post")
    public List<Posts> getListOfPosts() {
        return dashboardService.getListOfPosts();
    }


    /**
     * Retrieves the details of a post based on the application ID.
     * @author Ramandeep Kaur
     * @param applicationId The application ID.
     * @return The post details.
     */
    @GetMapping("/get/post/details/{applicationId}")
    public Posts getPostDetails(@PathVariable int applicationId) {
        if (applicationId <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }

        return dashboardService.getPostByApplicationId(applicationId);

    }

    /**
     * Retrieves the list of images for a post based on the application ID.
     *@author Ramandeep Kaur
     * @param applicationId The application ID.
     * @return The list of images.
     */
    @GetMapping("/get/list/images/{applicationId}")
    public List<String> getListOfImages(@PathVariable int applicationId) {
        if (applicationId <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return dashboardService.getListOfImagesByApplicationId(applicationId);
    }

    /**
     * Retrieves the list of food preferences for a post based on the application ID.
     *@author Ramandeep Kaur
     * @param applicationId The application ID.
     * @return The list of food preferences.
     */
    @GetMapping("/get/list/food/{applicationId}")
    public List<String> getListOfFoodPreferences(@PathVariable int applicationId) {
        if (applicationId <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return dashboardService.getListOfFoodPreferencesByApplicationId(applicationId);
    }

    /**
     * Retrieves the list of gender preferences for a post based on the application ID.
     *@author Ramandeep Kaur
     * @param applicationId The application ID.
     * @return The list of gender preferences.
     */
    @GetMapping("/get/list/gender/{applicationId}")
    public List<String> getListOfGenderPreferences(@PathVariable int applicationId) {
        if (applicationId <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return dashboardService.getListOfgenderPreferencesByApplicationId(applicationId);
    }
/*
    @GetMapping("/get/post/details/{applicationId}")
    public PostDetails getPostDetailss(@PathVariable int applicationId) {
        Posts post = dashboardService.getPostByApplicationId(applicationId);
        List<String> images = dashboardService.getListOfImagesByApplicationId(applicationId);
        List<String> foodPreferences = dashboardService.getListOfFoodPreferencesByApplicationId(applicationId);
        List<String> genderPreferences = dashboardService.getListOfgenderPreferencesByApplicationId(applicationId);

        return new PostDetails(post, images, foodPreferences, genderPreferences);
    }*/

    @GetMapping("/get/list/getListOfPersonalPosts/{user_Id}")
    public List<Posts> getListOfPersonalPosts(@PathVariable int user_Id) {
        if (user_Id <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return dashboardService.getListOfPersonalPosts(user_Id);
    }

}

