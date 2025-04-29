package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class DProgressDemoActivity extends AppCompatActivity {

    private ProgressBar pb_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dprogress_demo);

        pb_a = findViewById(R.id.pb_a);
    }

    public void btnOnClickA(View view) {
    }
}