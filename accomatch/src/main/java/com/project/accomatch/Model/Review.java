package com.project.accomatch.Model;


    public class Review {

        private Long id;

        private int rating;

        private String comment;

        public Review(Long id, int rating, String comment) {
            this.id = id;
            this.rating = rating;
            this.comment = comment;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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


