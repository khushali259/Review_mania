package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EnterDetails2 extends AppCompatActivity {

    Database db;
    EditText review;
    RatingBar ratingBar;
    Button btn;
    String movie_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details2);

        db = new Database();
        db.connectToDb();
        review = findViewById(R.id.description_edit);
        ratingBar = findViewById(R.id.rating);
        btn = findViewById(R.id.login);
        Intent intent = getIntent();
        movie_name = intent.getStringExtra("movie_name");
        Log.v("TAG", movie_name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReview();
            }
        });
    }

    private void addReview(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = preferences.getInt("user_id", 1);
        db.addReview(movie_name, review.getText().toString(), ratingBar.getRating(), userId);
        Toast.makeText(this, "Added Review", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}