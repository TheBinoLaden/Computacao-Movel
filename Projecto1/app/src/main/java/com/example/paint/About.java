package com.example.paint;

import android.os.Bundle;

public class About extends ParentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        super.setOrientation();
        super.setColorBackground(R.id.aboutPage);
    }
}