package com.project.accomatch.Controller;
import com.project.accomatch.Model.Ratings;
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
        return reviewServiceImplementation.getAllReviews(application_id);
    }
    @GetMapping("/getAverageRatings/{application_id}")
    public List<Ratings> getAverageRatings(@PathVariable int application_id){
        return reviewServiceImplementation.getRatingsAverage(application_id);
        /*Ratings ratings = reviewServiceImplementation.getRatingsAverage(application_id);
        List<Ratings> listOfReviews = new ArrayList<>();
        listOfReviews.add(ratings);
        return  listOfReviews;*/
    }
}
