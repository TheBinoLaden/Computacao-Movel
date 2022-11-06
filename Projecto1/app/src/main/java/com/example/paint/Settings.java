package com.example.paint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.widget.SwitchCompat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ResetUtils;

public class Settings extends ParentActivity {

    private static final String COLOR_PROP = "color_background";
    private static final String PREF_SETTINGS = "SettingsPref";
    private final List<SwitchCompat> switches = new ArrayList<>(3);
    private final Map<SwitchCompat, LocalTime> switchTimeClicked = new HashMap<>(3);
    private String color;
    private SwitchCompat colorGreen;
    private SwitchCompat darkBlue;
    private SwitchCompat changeButtonColor;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.setOrientation();
        super.setColorBackground(R.id.Settings);
        final SharedPreferences preferences =
                getApplicationContext().getSharedPreferences(PREF_SETTINGS, 0);

        initializeAttributes();
        setOnClick(preferences);
        checkPreferencesChecked(preferences);


    }

    public void changeColors() {

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
            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor("#7DB0D8"));
            colorGreen.setChecked(Boolean.FALSE);
            darkBlue.setChecked(Boolean.FALSE);
        } else {

            findViewById(R.id.Settings).setBackgroundColor(Color.parseColor(this.color));
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

    private void setOnClick(final SharedPreferences prefs) {


        colorGreen.setOnClickListener(view -> {
            if (colorGreen.isChecked()) {
                switchTimeClicked.replace(colorGreen, LocalTime.now());
                this.color = "#52BE80";
                changeColors();
            } else if (!colorGreen.isChecked() && !darkBlue.isChecked()) {
                this.color = "#7DB0D8";
                changeColors();
            }
        });
        darkBlue.setOnClickListener(view -> {
            if (darkBlue.isChecked()) {
                switchTimeClicked.replace(darkBlue, LocalTime.now());
                this.color = "#1A5276";
                changeColors();
            } else if (!darkBlue.isChecked() && !colorGreen.isChecked()) {
                this.color = "#7DB0D8";
                changeColors();
            }
        });
        back.setOnClickListener(view -> {
            final Intent intent = new Intent(Settings.this, MainActivity.class);
            intent.putExtra(COLOR_PROP, this.color);
            final SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("color_green", colorGreen.isChecked());
            editor.putBoolean("darkBlue", darkBlue.isChecked());
            editor.putBoolean("changeButtonColor", changeButtonColor.isChecked());
            editor.apply();
            startActivity(intent);
        });
    }

    private void checkPreferencesChecked(final SharedPreferences prefs) {

        final Map<String, ?> mapOfElements = prefs.getAll();
        if (Boolean.TRUE.equals(mapOfElements.get("color_green"))) {
            colorGreen.setChecked(Boolean.TRUE);
        } else {
            colorGreen.setChecked(Boolean.FALSE);
        }
        if (Boolean.TRUE.equals(mapOfElements.get("darkBlue"))) {
            darkBlue.setChecked(Boolean.TRUE);
        } else {
            darkBlue.setChecked(Boolean.FALSE);
        }
        if (Boolean.TRUE.equals(mapOfElements.get("changeButtonColor"))) {
            changeButtonColor.setChecked(Boolean.TRUE);
        } else {
            changeButtonColor.setChecked(Boolean.FALSE);
        }
    }

}