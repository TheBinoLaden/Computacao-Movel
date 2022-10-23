package com.example.paint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.MainActivityUtils;

public class MainActivity extends AppCompatActivity {

    private static final String COLOR_PROP = "color_background";
    private Button settings;
    private Button about;
    private Button canvas;
    private Button backCanvas;
    private Button paletteCanvas;
    private String color;
    private List<Button> mainButtons = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.color = getIntent().getStringExtra(COLOR_PROP);
        if (color == null) {
            findViewById(R.id.MainRelativeLayout).setBackgroundColor(Color.parseColor("#7DB0D8"));
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
            intent.putExtra(COLOR_PROP, this.color);
            startActivity(intent);
        });

        about.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, About.class);
            intent.putExtra(COLOR_PROP, this.color);
            startActivity(intent);
        });

        canvas.setOnClickListener(view -> {
            MainActivityUtils.canvasButtonAction(
                    getApplicationContext(), mainButtons, getSupportFragmentManager());
            backCanvas.setVisibility(View.VISIBLE);
            paletteCanvas.setVisibility(View.VISIBLE);
        });
        paletteCanvas.setOnClickListener(view ->
                MainActivityUtils.paletteButtonAction(mainButtons, getSupportFragmentManager()
                ));

        backCanvas.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra(COLOR_PROP, this.color);
            startActivity(intent);
        });
    }
}