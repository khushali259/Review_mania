package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

public class ViewParticularActivity extends AppCompatActivity {
    RatingBar rt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_particular);
        rt = (RatingBar) findViewById(R.id.rating);
        rt.setRating(3);
    }
}