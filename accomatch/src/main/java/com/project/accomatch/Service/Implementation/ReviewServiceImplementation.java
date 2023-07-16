package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Review;
import com.project.accomatch.Repository.ReviewRepository;
import com.project.accomatch.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;

    @Override
    public void createReview(Review review) {
        try {
            reviewRepository.createReview(review);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getAllReviews(int application_id) {
        return reviewRepository.getAllReviews(application_id);
    }


}