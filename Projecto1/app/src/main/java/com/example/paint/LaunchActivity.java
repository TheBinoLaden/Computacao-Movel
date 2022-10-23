package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    private static final String DEFAULT_COLOR_VALUE = "#7DB0D8";
    private static final String NAME_OF_PROP = "color_background";
    private Button startButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        startButton = findViewById(R.id.start);
        startButton.setOnClickListener(view -> {
            final Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            intent.putExtra(NAME_OF_PROP, DEFAULT_COLOR_VALUE);
            startActivity(intent);
        });
    }
}