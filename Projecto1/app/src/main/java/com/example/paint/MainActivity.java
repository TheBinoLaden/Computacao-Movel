package com.example.paint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fragments.CanvasFragment;
import fragments.PaletteFragment;

public class MainActivity extends AppCompatActivity {

    private Button settings, about, canvas, palette;

    private static final String PROP_FILE = "app.properties";
    private static final String COLOR_PROP = "color_background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Properties properties = new Properties();
            try (InputStream inputStream = getBaseContext().getAssets().open(PROP_FILE)) {
                properties.load(inputStream);
            }
            final String value = properties.getProperty(COLOR_PROP);
            findViewById(R.id.MainRelativeLayout).setBackgroundColor(Color.parseColor(value));
        } catch (final IOException e) {
            Log.e("ExceptionCaught", "error " + e.getMessage());
            e.printStackTrace();
        }
        initializeAttributes();
        setOnClick();

    }

    private void initializeAttributes() {
        settings = findViewById(R.id.settings);
        about = findViewById(R.id.about);
        canvas = findViewById(R.id.canvas);
        palette = findViewById(R.id.palette);
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

        canvas.setOnClickListener(view -> replaceFragment(new CanvasFragment()));
        palette.setOnClickListener(view -> replaceFragment(new PaletteFragment()));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}