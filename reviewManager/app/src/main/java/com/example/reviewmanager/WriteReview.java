package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class WriteReview extends AppCompatActivity {

    Button done ;
    FloatingActionButton fab;
    EditText enterMovie;
    MaterialBetterSpinner dropdown;
    int change;
    ArrayList<Movie> moviesArray;
    Database db = new Database();
    MaterialBetterSpinner betterSpinner;
    String movie_name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        db.connectToDb();
        betterSpinner = (MaterialBetterSpinner)findViewById(R.id.dropdown);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        done = (Button)findViewById(R.id.done);
        enterMovie = (EditText)findViewById(R.id.Enter_movie);
        dropdown = (MaterialBetterSpinner) findViewById(R.id.dropdown) ;
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(change==1){
                    Intent intent  = new Intent(WriteReview.this, EnterDetails.class);
                    intent.putExtra("movie_name", enterMovie.getText().toString());
                    WriteReview.this.startActivity(intent);
                    WriteReview.this.finish();

                }
                else{
                    Intent intent  = new Intent(getApplicationContext(),EnterDetails2.class);
                    intent.putExtra("movie_name", movie_name);
                    WriteReview.this.startActivity(intent);
                    WriteReview.this.finish();
                }

            }
        });
        betterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                movie_name = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(WriteReview.this, movie_name, Toast.LENGTH_SHORT).show();

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Enter the movie",Toast.LENGTH_SHORT).show();
                enterMovie.setVisibility(View.VISIBLE);
                dropdown.setVisibility(View.GONE);
                change++;
            }
        });
        listAllMovies();
    }


    private void listAllMovies(){
        moviesArray = db.listMovies();
        String [] SpinnerList = new String[moviesArray.size()];
        for(int i = 0; i<moviesArray.size();i++){
            SpinnerList[i] = moviesArray.get(i).movie_name;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,SpinnerList);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner)findViewById(R.id.dropdown);
        betterSpinner.setAdapter(arrayAdapter);
    }
}