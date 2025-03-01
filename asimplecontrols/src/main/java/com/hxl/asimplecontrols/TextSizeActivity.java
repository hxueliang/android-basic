package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TextSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_size);

        TextView tv = findViewById(R.id.tv_java_set_24sp);
        tv.setTextSize(24); // 单位sp
    }
}