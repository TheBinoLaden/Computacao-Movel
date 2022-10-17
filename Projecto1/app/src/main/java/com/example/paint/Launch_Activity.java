package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import utils.SharePreferencesUtils;

public class Launch_Activity extends AppCompatActivity {

    private Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharePreferencesUtils.putInformation(pref, "default_color", "#7DB0D8");
        findViewById(R.id.LaunchConstraintPage).setBackgroundColor(Color.parseColor("#7DB0D8"));
        button = findViewById(R.id.start);
        button.setOnClickListener(view -> {
            final Intent intent = new Intent(Launch_Activity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}