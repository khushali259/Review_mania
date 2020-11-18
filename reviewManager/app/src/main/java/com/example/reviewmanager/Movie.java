package com.example.reviewmanager;

public class Movie {
    public int movie_id;
    public String movie_name;
    public String director;
    public int year_of_release;
    public float overall_rating;
    public int no_reviews;

    public Movie(int movie_id, String movie_name, String director, int year_of_release, float overall_rating, int no_reviews) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.director = director;
        this.year_of_release = year_of_release;
        this.overall_rating = overall_rating;
        this.no_reviews = no_reviews;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getDirector() {
        return director;
    }

    public int getYear_of_release() {
        return year_of_release;
    }

    public float getOverall_rating() {
        return overall_rating;
    }

    public int getNo_reviews() {
        return no_reviews;
    }
}
