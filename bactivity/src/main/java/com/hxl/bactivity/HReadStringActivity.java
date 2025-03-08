package com.hxl.bactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HReadStringActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_show_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hread_string);

        tv_show_string = findViewById(R.id.tv_show_string);
        findViewById(R.id.btn_get_string).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}