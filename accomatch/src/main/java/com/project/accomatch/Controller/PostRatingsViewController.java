package com.project.accomatch.Controller;
import com.project.accomatch.Model.Review;
import com.project.accomatch.Service.Implementation.ReviewServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/reviews")
public class PostRatingsViewController {
    @Autowired
    private ReviewServiceImplementation reviewServiceImplementation;
    @GetMapping("/getListOfAllRatings/{application_id}")
    public List<Review> getListOfAllRatings(@PathVariable int application_id){
        return reviewServiceImplementation.getAllReviews(application_id);
    }
}
