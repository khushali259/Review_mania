package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ParticluarMovieReviews extends AppCompatActivity {
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particluar_movie_reviews);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.movies_list);
        listView.setAdapter(adapter);
    }
}