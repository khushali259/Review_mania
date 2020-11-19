package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewReview extends AppCompatActivity {
    private Database db;
    private ListView listView;
    ArrayList<Movie> moviesArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);
        db = new Database();
        db.connectToDb();
        listView = (ListView) findViewById(R.id.movie_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ViewReview.this, ViewParticularReview.class);
                int mid = moviesArray.get(position).movie_id;
                i.putExtra("movie_id", mid);
                ViewReview.this.startActivity(i);
                ViewReview.this.finish();
            }
        });

        listAllMovies();
    }

    private void listAllMovies(){
        moviesArray = db.listMovies();
        ViewReviewListAdapter adapter = new ViewReviewListAdapter(this, R.layout.listview_item2, moviesArray);


        listView.setAdapter(adapter);
    }

}