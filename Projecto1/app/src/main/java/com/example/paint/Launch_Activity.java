package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Launch_Activity extends AppCompatActivity {

    private Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        button = (Button) findViewById(R.id.start);
        button.setOnClickListener(view -> {
            final Intent intent = new Intent(Launch_Activity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}