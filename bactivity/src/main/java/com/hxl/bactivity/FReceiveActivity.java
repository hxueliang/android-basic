package com.hxl.bactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FReceiveActivity extends AppCompatActivity {

    private TextView tv_show_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freceive);

        tv_show_message = findViewById(R.id.tv_show_message);
    }
}