package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class ViewParticularReview extends AppCompatActivity {
    RatingBar rt;
    Button seeAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_particular_review);
        rt = (RatingBar) findViewById(R.id.rating);
        rt.setRating(3);
        seeAll =(Button)findViewById(R.id.see_all);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ParticluarMovieReviews.class);
                startActivity(i);
            }
        });
    }
}