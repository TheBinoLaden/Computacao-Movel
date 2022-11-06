package com.example.paint;

import android.os.Bundle;

public class MapActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setOrientation();
        setContentView(R.layout.activity_map);

        super.setColorBackground(R.id.MapPage);
    }
}
