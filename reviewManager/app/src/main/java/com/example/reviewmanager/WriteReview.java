package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class WriteReview extends AppCompatActivity {

    String [] SpinnerList = {"Avatar","Titanic","Avengers:Age of Ultron","Captain America","Others"};
    Button done ;
    FloatingActionButton fab;
    EditText enterMovie;
    MaterialBetterSpinner dropdown;
    int change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,SpinnerList);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner)findViewById(R.id.dropdown);
        betterSpinner.setAdapter(arrayAdapter);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        done = (Button)findViewById(R.id.done);
        enterMovie = (EditText)findViewById(R.id.Enter_movie);
        dropdown = (MaterialBetterSpinner) findViewById(R.id.dropdown) ;
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(change==1){
                    Intent intent  = new Intent(getApplicationContext(),EnterDetails.class);
                    startActivity(intent);

                }
                else{
                    Intent intent  = new Intent(getApplicationContext(),EnterDetails2.class);
                    startActivity(intent);
                }

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

    }

}