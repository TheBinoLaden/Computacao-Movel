package com.example.paint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ResetUtils;
import utils.SharePreferencesUtils;

public class Settings extends AppCompatActivity {

    private SwitchCompat colorGreen;
    private SwitchCompat darkBlue;
    private SwitchCompat changeButtonColor;
    private final List<SwitchCompat> switches = new ArrayList<>(3);
    private Button back;
    private final Map<SwitchCompat, LocalTime> switchTimeClicked = new HashMap<>(3);
    private static final String COLOR_PROP = "color_background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String color = (String) SharePreferencesUtils.getInformation(pref, COLOR_PROP);

        if (color == null) {
            color = (String) SharePreferencesUtils.getInformation(pref, "default_color");
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(color));
        } else {
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(color));
        }
        initializeAttributes();
        setOnClick(pref);


    }

    public void changeColors(final SharedPreferences sharedPreferences) {

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
            String color = (String) SharePreferencesUtils.getInformation(sharedPreferences,
                    "default_color");
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(color));
        } else {
            String color = (String) SharePreferencesUtils.getInformation(sharedPreferences, COLOR_PROP);
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(color));
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

    private void setOnClick(final SharedPreferences sharedPreferences) {


        colorGreen.setOnClickListener(view -> {
            if (colorGreen.isChecked()) {
                switchTimeClicked.replace(colorGreen, LocalTime.now());
                SharePreferencesUtils.putInformation(sharedPreferences, COLOR_PROP, "#52BE80");
                changeColors(sharedPreferences);
            } else {
                ResetUtils.resetToColor(sharedPreferences, colorGreen, darkBlue);
            }
        });
        darkBlue.setOnClickListener(view -> {
            if (darkBlue.isChecked()) {
                switchTimeClicked.replace(darkBlue, LocalTime.now());
                SharePreferencesUtils.putInformation(sharedPreferences, COLOR_PROP, "#1A5276");
                changeColors(sharedPreferences);
            } else {
                ResetUtils.resetToColor(sharedPreferences, darkBlue, colorGreen);
            }
        });
        back.setOnClickListener(view -> {
            final Intent intent = new Intent(Settings.this, MainActivity.class);
            startActivity(intent);
        });
    }

}