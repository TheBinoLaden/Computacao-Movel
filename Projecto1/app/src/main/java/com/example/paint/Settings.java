package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Settings extends AppCompatActivity {

    private SwitchCompat color_green;
    private SwitchCompat dark_blue;
    private SwitchCompat change_button_color;
    private final List<SwitchCompat> switches = new ArrayList<>(3);
    private Button back;
    private final Map<SwitchCompat, LocalTime> switchTimeClicked = new HashMap<>(3);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeAttributes();
        setOnClick();
    }

    public void changeColors(View view) {

        for (SwitchCompat switchCompat : switches) {
            if (switchCompat.isChecked()) {
                Log.d("ColorSelected", "onClick registered." +
                        " the button associated with the " + switchCompat
                        + " color, for the background/button, is selected");

            }
        }
        if (color_green.isChecked() && dark_blue.isChecked()) {
            Log.e("BothChecked", "Both buttons are checked, reverting to default.");
            ResetUtils.validateLastChecked(
                    Arrays.asList(color_green, dark_blue),
                    Arrays.asList(switchTimeClicked.get(color_green),
                            switchTimeClicked.get(dark_blue)));
        }
    }

    private void initializeAttributes() {
        color_green = findViewById(R.id.greenColor);
        dark_blue = findViewById(R.id.darker_blue);
        change_button_color = findViewById(R.id.change_color);
        back = findViewById(R.id.back_settings);
        populateList();
        populateMap();
    }

    private void populateList() {
        switches.add(color_green);
        switches.add(dark_blue);
        switches.add(change_button_color);
    }

    private void populateMap() {
        switchTimeClicked.put(color_green, null);
        switchTimeClicked.put(dark_blue, null);
        switchTimeClicked.put(change_button_color, null);
    }

    private void setOnClick() {
        color_green.setOnClickListener(view -> {
            switchTimeClicked.replace(color_green, LocalTime.now());
            changeColors(view);
        });
        dark_blue.setOnClickListener(view -> {
            switchTimeClicked.replace(dark_blue, LocalTime.now());
            changeColors(view);
        });
        back.setOnClickListener(view -> {
            final Intent intent = new Intent(Settings.this, MainActivity.class);
            startActivity(intent);
        });
    }

}