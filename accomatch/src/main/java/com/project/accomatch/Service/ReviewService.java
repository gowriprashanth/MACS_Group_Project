package com.project.accomatch.Service;

import com.project.accomatch.Model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);

    List<Review> getAllReviews(int application_id);
    // Other methods for retrieving, updating, deleting reviews
}
