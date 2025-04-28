package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CAsyncMessageActivity extends AppCompatActivity {

    private Button btn_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casync_message);

        btn_a = findViewById(R.id.btn_a);
    }

    public void btnOnClickA(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // btn_a.setText("模似接口返回数据"); // 报错，不ui操作只能的主线程进行
            }
        }).start();
    }
}