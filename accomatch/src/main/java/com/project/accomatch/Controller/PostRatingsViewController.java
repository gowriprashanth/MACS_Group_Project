package com.project.accomatch.Controller;
import com.project.accomatch.Model.Ratings;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Model.Review;
import com.project.accomatch.Service.Implementation.ReviewServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/reviews")
public class PostRatingsViewController {
    @Autowired
    private ReviewServiceImplementation reviewServiceImplementation;
    @GetMapping("/getListOfAllRatings/{application_id}")
    public List<Review> getListOfAllRatings(@PathVariable int application_id){
        if (application_id <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return reviewServiceImplementation.getAllReviews(application_id);
    }
    @GetMapping("/getAverageRatings/{application_id}")
    public List<Ratings> getAverageRatings(@PathVariable int application_id){
        if (application_id <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return reviewServiceImplementation.getRatingsAverage(application_id);
    }

    @GetMapping("/getAllPostReviews")
    public List<Review> getAllPostReviews(){
        return reviewServiceImplementation.getAllPostReviews();
    }

    @GetMapping("/getAllAverageRatings")
    public List<Ratings> getAllAverageRatings(){
        return reviewServiceImplementation.getAllRatingsAverage();
    }
}
