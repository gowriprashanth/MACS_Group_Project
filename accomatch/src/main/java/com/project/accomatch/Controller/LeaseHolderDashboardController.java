package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.LeaseHolderDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/dashboard")
public class LeaseHolderDashboardController {
    @Autowired
    private  LeaseHolderDashboardService dashboardService;

    @GetMapping("/get/list/post")
    public List<Posts> getListOfPosts() {
        return dashboardService.getListOfPosts();
    }
}
