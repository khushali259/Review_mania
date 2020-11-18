package com.example.reviewmanager;

public class Review {
    public int review_id;
    public int movie_id;
    public String review;
    public float rating;
    public int userid;


    public Review(int review_id, int movie_id, String review, float rating, int userid) {
        this.review_id = review_id;
        this.movie_id = movie_id;
        this.review = review;
        this.rating = rating;
        this.userid = userid;
    }

    public int getReview_id() {
        return review_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public String getReview() {
        return review;
    }

    public float getRating() {
        return rating;
    }

    public int getUserid() {
        return userid;
    }
}
