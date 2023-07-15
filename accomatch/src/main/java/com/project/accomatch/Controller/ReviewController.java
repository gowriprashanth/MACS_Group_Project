package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Model.Review;
import com.project.accomatch.Service.AdminInterface;
import com.project.accomatch.Service.Implementation.LeaseHolderDashboardService;
import com.project.accomatch.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

}
