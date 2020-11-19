package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ParticluarMovieReviews extends AppCompatActivity {

    Database db = new Database();
    int movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particluar_movie_reviews);
        Intent intent = getIntent();
        movie_id = intent.getIntExtra("movie_id", 1);
        db.connectToDb();
        ArrayList<Review> reviews = db.listMovieReviews(movie_id);
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow, reviews);

        ListView listView = (ListView) findViewById(R.id.movies_list);
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ParticluarMovieReviews.this, ViewReview.class);
        ParticluarMovieReviews.this.startActivity(intent);
        ParticluarMovieReviews.this.finish();
        //super.onBackPressed();
    }
}