package com.example.furniture_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton home = findViewById(R.id.HomeScreen);
        home.setOnClickListener(view -> {
            Intent homeIntent = new Intent(this,HomeActivity.class);
            startActivity(homeIntent);
        });
    }

}