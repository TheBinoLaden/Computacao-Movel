package com.example.paint;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    private static final String COLOR_PROP = "color_background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        boolean t = COLOR_PROP.equals("color_background");
        final String color = getIntent().getExtras().getString(COLOR_PROP);
        findViewById(R.id.aboutPage).setBackgroundColor(Color.parseColor(color));
    }
}