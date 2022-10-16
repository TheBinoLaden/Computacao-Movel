package com.example.paint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.MainActivityUtils;
import utils.SharePreferencesUtils;

public class MainActivity extends AppCompatActivity {

    private Button settings;
    private Button about;
    private Button canvas;
    private Button backCanvas;
    private Button paletteCanvas;

    private List<Button> mainButtons = new ArrayList<>(3);

    private static final String COLOR_PROP = "color_background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String color = (String) SharePreferencesUtils.getInformation(pref, COLOR_PROP);

        if (color == null) {
            color = (String) SharePreferencesUtils.getInformation(pref, "default_color");
            findViewById(R.id.MainRelativeLayout).setBackgroundColor(Color.parseColor(color));
        } else {
            findViewById(R.id.MainRelativeLayout).setBackgroundColor(Color.parseColor(color));
        }

        initializeAttributes();
        setOnClick();

    }

    private void initializeAttributes() {
        settings = findViewById(R.id.settings);
        about = findViewById(R.id.about);
        canvas = findViewById(R.id.canvas);
        mainButtons = Arrays.asList(settings, about, canvas);

        //Canvas button
        backCanvas = findViewById(R.id.backMain);
        paletteCanvas = findViewById(R.id.palette);
        backCanvas.setVisibility(View.GONE);
        paletteCanvas.setVisibility(View.GONE);
    }

    private void setOnClick() {
        settings.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        });

        about.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        });

        canvas.setOnClickListener(view -> {
            MainActivityUtils.canvasButtonAction(
                    getApplicationContext(), mainButtons, getSupportFragmentManager());
            backCanvas.setVisibility(View.VISIBLE);
            paletteCanvas.setVisibility(View.VISIBLE);
        });
        paletteCanvas.setOnClickListener(view -> MainActivityUtils.paletteButtonAction(
                mainButtons, getSupportFragmentManager()));

        backCanvas.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}