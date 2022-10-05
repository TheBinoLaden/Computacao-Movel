package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button settings;
    private Button about;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (Button) findViewById(R.id.settings);

        about = (Button) findViewById(R.id.about);
        settings.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, Launch_Activity.class);
            startActivity(intent);
        });

        about.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, Launch_Activity.class);
            startActivity(intent);
        });

    }
}