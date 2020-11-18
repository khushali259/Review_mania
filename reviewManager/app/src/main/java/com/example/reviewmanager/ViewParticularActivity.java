package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewParticularActivity extends AppCompatActivity {
    RatingBar rt;
    Database db;
    int movie_id;
    private TextView movie_name_text, director_name_text, year_text;
    private EditText review_text;
    private int review_id;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_particular);
        db = new Database();
        Intent intent = getIntent();
        movie_id = intent.getIntExtra("movie_id", 1);
        db.connectToDb();
        rt = findViewById(R.id.rating);
        movie_name_text = findViewById(R.id.movie_name_text);
        director_name_text = findViewById(R.id.director_name_text);
        year_text = findViewById(R.id.year_text);
        review_text = findViewById(R.id.review_text);
        btn = findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editReview();
            }
        });
        showMovie();
        showReview();

    }

    private void editReview(){
        db.editReview(review_text.getText().toString(), rt.getRating(), review_id);
        super.finish();
    }

    private void showMovie(){
        Movie movie = db.getMovie(movie_id);
        movie_name_text.setText(movie.movie_name);
        director_name_text.setText(movie.director);
        year_text.setText(String.valueOf(movie.year_of_release));
    }

    private void showReview(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = preferences.getInt("user_id", 1);
        Review review = db.showReview(userId, movie_id);
        review_id = review.review_id;
        review_text.setText(review.review);
        rt.setRating(review.rating);
    }
}