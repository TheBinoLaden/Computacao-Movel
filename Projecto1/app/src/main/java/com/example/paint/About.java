package com.example.paint;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import utils.SharePreferencesUtils;

public class About extends AppCompatActivity {

    private static final String COLOR_PROP = "color_background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String color = (String) SharePreferencesUtils.getInformation(pref, COLOR_PROP);

        if (color == null) {
            color = (String) SharePreferencesUtils.getInformation(pref, "default_color");
            findViewById(R.id.aboutPage).setBackgroundColor(Color.parseColor(color));
        } else {
            findViewById(R.id.aboutPage).setBackgroundColor(Color.parseColor(color));
        }
    }
}