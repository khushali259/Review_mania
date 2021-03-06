package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
    LinearLayout l1;
    LinearLayout l2;
    LinearLayout l3;
    ImageView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        l1=(LinearLayout)findViewById(R.id.view_review);
        l2=(LinearLayout)findViewById(R.id.View_activity);
        l3=(LinearLayout)findViewById(R.id.write_review);
        logout=findViewById(R.id.logout);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ViewReview.class);
                HomeActivity.this.startActivity(intent);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ViewActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,WriteReview.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("user_id",-1);
                editor.apply();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                HomeActivity.this.startActivity(intent);
                HomeActivity.this.finish();
            }
        });
    }

}