package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private Switch color_green;
    private Switch dark_blue;
    private Switch change_button_color;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        color_green = (Switch) findViewById(R.id.greenColor);
        dark_blue = (Switch) findViewById(R.id.darker_blue);
        change_button_color = (Switch) findViewById(R.id.change_color);

        color_green.setOnClickListener(view -> {

        });
    }
}