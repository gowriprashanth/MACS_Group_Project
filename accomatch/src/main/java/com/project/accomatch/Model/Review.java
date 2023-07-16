package com.project.accomatch.Model;


    public class Review {

        private int ratingId;

        private int userId;

        private int applicationId;
        private int rating;

        private String comment;

        private String name;

        public Review(int ratingId, int userId, int applicationId, String name, int rating, String comment) {
            this.ratingId = ratingId;
            this.userId = userId;
            this.applicationId = applicationId;
            this.name = name;
            this.rating = rating;
            this.comment = comment;
        }

        public int getRatingId() {
            return ratingId;
        }
        public int getUserId() {
            return userId;
        }
        public int getApplicationId() {
            return applicationId;
        }

        public void setUserId(int id) {
            this.userId = id;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

    }


