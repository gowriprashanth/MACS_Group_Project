package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/dashboard")
public class LeaseholderDashboard {


    @GetMapping("/get/list/post")
    public List<Posts> getListOfPosts() {
        List<Posts> listOfPosts = new ArrayList<>();
        Posts application = new Posts(
                12345,
                12345667,
                "Application Title",
                "Application Subtitle",
                "123 Main Street",
                "City Name",
                1000,
                "Single Room",
                "https://images1.apartments.com/i2/991otg0L0cjJomys4aNphYO9A-iDOaMaGC1NHsWzYSQ/111/baker-arms-wexford-apartments-dartmouth-ns-primary-photo.jpg?p=1", // Image URL
                500.00,
                "Other Preferences",
                new Date(),
                20,
                30,
                true,
                new Date(),
                new Date()
        );
        listOfPosts.add(application);

        return listOfPosts;
    }
}
