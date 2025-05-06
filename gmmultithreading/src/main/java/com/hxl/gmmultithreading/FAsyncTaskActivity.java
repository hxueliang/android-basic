package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FAsyncTaskActivity extends AppCompatActivity {

    private Button btn_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasync_task);

        btn_a = findViewById(R.id.btn_a);
    }

    public void btnOnClickA(View view) {
    }
}