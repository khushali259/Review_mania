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

                Intent i = new Intent(getApplicationContext(), ViewParticularReview.class);
                int mid = moviesArray.get(position).movie_id;
                i.putExtra("movie_id", mid);
                startActivity(i);
            }
        });

        listAllMovies();
    }

    private void listAllMovies(){
        moviesArray = db.listMovies();
        String[] movieNames = new String[moviesArray.size()];
        for(int i = 0; i<moviesArray.size();i++){
            movieNames[i] = moviesArray.get(i).movie_name;
        }
        Toast.makeText(this, "filled list", Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview_item, movieNames);

        listView.setAdapter(adapter);

        Toast.makeText(this, String.valueOf(moviesArray.size()), Toast.LENGTH_SHORT).show();
    }

}