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

public class EnterDetails extends AppCompatActivity {

    String movie_name;
    Database db;

    EditText title, director, year, review;
    RatingBar ratingBar;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        db = new Database();
        Intent intent = getIntent();
        movie_name = intent.getStringExtra("movie_name");
        db.connectToDb();

        title = findViewById(R.id.movie_name_edit);
        title.setText(movie_name);
        director = findViewById(R.id.director_edit);
        year = findViewById(R.id.year_edit);
        review = findViewById(R.id.description_edit);
        ratingBar = findViewById(R.id.rating);
        btn = findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertReview();
            }
        });
    }


    private void insertReview(){
        String name = title.getText().toString();
        String directorText = director.getText().toString();
        int year_release = Integer.parseInt(year.getText().toString());
        String reviewText = review.getText().toString();
        float ratingVal = ratingBar.getRating();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = preferences.getInt("user_id", 1);
        db.addNewReview(name, directorText, year_release, reviewText, ratingVal, userId);

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}