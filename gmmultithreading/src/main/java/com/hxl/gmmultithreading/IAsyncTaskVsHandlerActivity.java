package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IAsyncTaskVsHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iasync_task_vs_handler);

        final TextView tv_a = findViewById(R.id.tv_a);
    }
}