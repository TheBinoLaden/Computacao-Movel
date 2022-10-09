package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import utils.ResetUtils;

public class Settings extends AppCompatActivity {

    private SwitchCompat colorGreen;
    private SwitchCompat darkBlue;
    private SwitchCompat changeButtonColor;
    private final List<SwitchCompat> switches = new ArrayList<>(3);
    private Button back;
    private final Map<SwitchCompat, LocalTime> switchTimeClicked = new HashMap<>(3);
    private static final String PROP_FILE = "app.properties";
    private static final String COLOR_PROP = "color_background";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try {
            Properties properties = new Properties();
            try (InputStream inputStream = getBaseContext().getAssets().open(PROP_FILE)) {
                properties.load(inputStream);
            }
            final String value = properties.getProperty(COLOR_PROP);

            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(value));
            initializeAttributes();
            setOnClick(properties);

        } catch (final IOException e) {
            Log.e("ExceptionCaught", "error " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void changeColors(final Properties properties) {

        for (SwitchCompat switchCompat : switches) {
            if (switchCompat.isChecked()) {
                Log.d("ColorSelected", "onClick registered." +
                        " the button associated with the " + switchCompat
                        + " color, for the background/button, is selected");

            }
        }
        if (colorGreen.isChecked() && darkBlue.isChecked()) {
            Log.e("BothChecked", "Both buttons are checked, reverting to default.");
            ResetUtils.validateLastChecked(
                    Arrays.asList(colorGreen, darkBlue),
                    Arrays.asList(switchTimeClicked.get(colorGreen),
                            switchTimeClicked.get(darkBlue)));
            properties.setProperty(COLOR_PROP, "#7DB0D8");
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor("#7DB0D8"));
        } else {
            final String value = properties.getProperty(COLOR_PROP);
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(value));
        }
    }

    private void initializeAttributes() {
        colorGreen = findViewById(R.id.greenColor);
        darkBlue = findViewById(R.id.darker_blue);
        changeButtonColor = findViewById(R.id.change_color);
        back = findViewById(R.id.back_settings);
        populateList();
        populateMap();
    }

    private void populateList() {
        switches.add(colorGreen);
        switches.add(darkBlue);
        switches.add(changeButtonColor);
    }

    private void populateMap() {
        switchTimeClicked.put(colorGreen, null);
        switchTimeClicked.put(darkBlue, null);
        switchTimeClicked.put(changeButtonColor, null);
    }

    private void setOnClick(final Properties properties) {


        colorGreen.setOnClickListener(view -> {
            switchTimeClicked.replace(colorGreen, LocalTime.now());
            properties.setProperty(COLOR_PROP, "#52BE80");
            changeColors(properties);
        });
        darkBlue.setOnClickListener(view -> {
            switchTimeClicked.replace(darkBlue, LocalTime.now());
            properties.setProperty(COLOR_PROP, "#1A5276");
            changeColors(properties);
        });
        back.setOnClickListener(view -> {
            final Intent intent = new Intent(Settings.this, MainActivity.class);
            startActivity(intent);
        });
    }

}