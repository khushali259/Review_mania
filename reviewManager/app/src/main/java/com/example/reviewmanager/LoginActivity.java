package com.example.reviewmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    private Database db;

    EditText username, password;
    Button login;
    TextView register;
    boolean isUsernameValid, isPasswordValid;
    TextInputLayout usernameError, passError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        db = new Database();
        String str = db.connectToDb();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        usernameError = (TextInputLayout) findViewById(R.id.usernameError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
                //LoginActivity.this.finish();
            }
        });
    }
    public void SetValidation() {
        // Check for a valid email address.
        if (username.getText().toString().isEmpty()) {
            usernameError.setError(getResources().getString(R.string.email_error));
            isUsernameValid = false;
        }  else  {
            isUsernameValid = true;
            usernameError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 5) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isUsernameValid && isPasswordValid) {
            loginUser();
        }

    }

    private void loginUser(){
        String name = username.getText().toString();
        String pswd = password.getText().toString();
        int res = db.loginUser(name, pswd);
        if(res>0){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("user_id",res);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
        }else if(res==0){
            Toast.makeText(this, "Password or username is incorrect", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Cannot log in", Toast.LENGTH_SHORT).show();
        }
    }

}