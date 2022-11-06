package com.example.paint;

import android.content.pm.ActivityInfo;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

public class ParentActivity extends AppCompatActivity {

    protected static final String COLOR_PROP = "color_background";
    protected String color;


    protected void setColorBackground(final Integer layoutID) {
        color = getIntent().getStringExtra(COLOR_PROP);
        if (color == null) {
            findViewById(layoutID).setBackgroundColor(Color.parseColor("#7DB0D8"));
        } else {
            findViewById(layoutID).setBackgroundColor(Color.parseColor(color));
        }
    }

    protected void setOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }
}
