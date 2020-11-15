package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
    LinearLayout l1;
    LinearLayout l2;
    LinearLayout l3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        l1=(LinearLayout)findViewById(R.id.view_review);
        l2=(LinearLayout)findViewById(R.id.View_activity);
        l3=(LinearLayout)findViewById(R.id.write_review);

        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(),ViewReview.class);
                startActivity(intent);
                return true;
            }
        });

        l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(),ViewActivity.class);
                startActivity(intent);
                return true;
            }
        });

        l3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(),WriteReview.class);
                startActivity(intent);
                return true;
            }
        });
    }
}