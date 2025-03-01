package com.hxl.asimplecontrols;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TextColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_color);

        TextView tv = findViewById(R.id.tv_text_color);
        // tv.setTextColor(Color.RED);
        tv.setTextColor(0xFFFF0000);
    }
}