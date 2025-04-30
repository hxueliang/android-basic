package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DProgressDemoActivity extends AppCompatActivity {

    private ProgressBar pb_a;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dprogress_demo);

        pb_a = findViewById(R.id.pb_a);
    }

    public void btnOnClickA(View view) {
        pb_a.setVisibility(View.VISIBLE);
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    if (progress < 100) {
                        progress += 10;
                        pb_a.setProgress(progress);
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }
        }
    };
}