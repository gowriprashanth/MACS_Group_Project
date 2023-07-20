package com.project.accomatch.Controller;

import com.project.accomatch.Model.Review;
import com.project.accomatch.Service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/createReview")
    public void createReview(@RequestBody Review review) {
        int user_id = review.getUserId();
        int application_id = review.getApplicationId();
        reviewService.createReview(review);
    }

    /*@GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }*/

}
