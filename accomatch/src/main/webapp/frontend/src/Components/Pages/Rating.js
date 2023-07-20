import React, { useState } from 'react';
import axios from 'axios';

export const Rating = () => {
    const [reviews, setReviews] = useState([]);
    const [rating, setRating] = useState(0);
    const [comment, setComment] = useState('');

    const handleRatingChange = (event) => {
        setRating(event.target.value);
    };

    const handleCommentChange = (event) => {
        setComment(event.target.value);
    };

    const handleSubmitReview = () => {
        const newReview = { rating, comment };
        // Add code to submit the review to the backend API
        // For example, using Axios:
        const authToken = sessionStorage.getItem("token"); // Replace with the actual authentication token
        
        axios.post('/api/reviews', newReview, {
          headers: {
            Authorization: `Bearer ${authToken}` // Include the authentication token in the headers
          }
        })
          .then((response) => {
            // Update the reviews state with the newly submitted review
            setReviews([...reviews, response.data]);
            // Clear the rating and comment inputs
            setRating(0);
            setComment('');
          })
          .catch((error) => {
            console.error('Error submitting review:', error);
          });
      };
      

    return (
        <div>
            <h2>Reviews</h2>
            {reviews.map((review) => (
                <ReviewItem key={review.id} review={review} />
            ))}
            <div>
                <h3>Add a Review</h3>
                <div>
                    <label>Rating:</label>
                    <input type="number" min={1} max={5} value={rating} onChange={handleRatingChange} />
                </div>
                <div>
                    <label>Comment:</label>
                    <textarea value={comment} onChange={handleCommentChange} />
                </div>
                <button onClick={handleSubmitReview}>Submit Review</button>
            </div>
        </div>
    );
};

const ReviewItem = ({ review }) => {
    return (
        <div>
            <div>Rating: {review.rating}</div>
            <div>Comment: {review.comment}</div>
        </div>
    );
};