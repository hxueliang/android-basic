package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CDatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdatabase);

        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        tv_message = findViewById(R.id.tv_message);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_create) {
        } else if (v.getId() == R.id.btn_delete) {
        }
    }
}