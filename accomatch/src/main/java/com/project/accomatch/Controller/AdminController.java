package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.Implementation.LeaseHolderDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public class AdminController {


    @Autowired
    private LeaseHolderDashboardService dashboardService;

    /**
     * Retrieves the list of posts for Admin.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @GetMapping("/get/list/post")
    public List<Posts> getListOfPosts() {
        return dashboardService.getListOfPosts();
    }

    /**
     * Retrieves the list of posts for Admin bases on status.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @GetMapping("/get/list/post/{status}")
    public List<Posts> getListOfPostsByStatus(int status)
    {
        return dashboardService.getListOfPostsByStatus(status);
    }


}
