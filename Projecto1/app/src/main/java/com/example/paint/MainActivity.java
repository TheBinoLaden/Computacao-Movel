package com.example.paint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.MainActivityUtils;

public class MainActivity extends ParentActivity {

    private static final String COLOR_PROP = "color_background";
    private Button settings;
    private Button about;
    private Button canvas;
    private Button backCanvas;
    private Button paletteCanvas;
    private Button map;
    private List<Button> mainButtons = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.setOrientation();
        super.setColorBackground(R.id.MainRelativeLayout);

        initializeAttributes();
        setOnClick();


    }

    private void initializeAttributes() {
        settings = findViewById(R.id.settings);
        about = findViewById(R.id.about);
        canvas = findViewById(R.id.canvas);
        map = findViewById(R.id.map);
        mainButtons = Arrays.asList(settings, about, canvas, map);

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

        map.setOnClickListener(view -> {
            final Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra(COLOR_PROP, this.color);
            startActivity(intent);
        });

        // Fragments and back Button

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