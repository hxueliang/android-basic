package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EAsyncMessageActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
            }
        }
    };
    private Button btn_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easync_message);

        btn_b = findViewById(R.id.btn_b);
    }

    public void btnOnClickA(View view) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("x_log", "我在主线程：handler post");
            }
        });
    }

    public void btnOnClickB(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btn_b.setText("post发出的修改UI事件");
                    }
                });
            }
        }).start();
    }

    public void btnOnClickC(View view) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("x_log", "我在主线程：handler postDelayed");
            }
        }, 2 * 1000);
    }
}