package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewParticularReview extends AppCompatActivity {
    RatingBar rt;
    Button seeAll;
    private  Database db;
    int mid;
    TextView movie_name_text, director_text, year_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_particular_review);
        Intent intent = getIntent();
        mid = intent.getIntExtra("movie_id", 1);
        db = new Database();
        db.connectToDb();
        rt = (RatingBar) findViewById(R.id.rating);
        movie_name_text = findViewById(R.id.movie_name_text);
        director_text = findViewById(R.id.director_name_text);
        year_text = findViewById(R.id.year_text);

        seeAll =(Button)findViewById(R.id.see_all);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ParticluarMovieReviews.class);
                i.putExtra("movie_id", mid);
                startActivity(i);
            }
        });

        showReview();
    }


    private void showReview(){
        Movie movie = db.getMovie(mid);
        movie_name_text.setText(movie.movie_name);
        director_text.setText(movie.director);
        year_text.setText(String.valueOf(movie.year_of_release));
        rt.setRating(movie.overall_rating);
    }
}