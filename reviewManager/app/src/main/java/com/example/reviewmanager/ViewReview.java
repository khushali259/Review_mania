package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewReview extends AppCompatActivity {
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.movie_list);
        listView.setAdapter(adapter);


    }
}