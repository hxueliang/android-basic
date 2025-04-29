package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CAsyncMessageActivity extends AppCompatActivity {

    private Button btn_a;
    private Button btn_b;
    private Button btn_c;
    private Button btn_d;

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String str = "";
            switch (msg.what) {
                case 1:
                    btn_a.setText("sendEmptyMessage");
                    break;
                case 2:
                    btn_b.setText("sendEmptyMessageDelayed");
                    break;
                case 3:
                    str = (String) msg.obj;
                    btn_c.setText("sendMessage " + str);
                    break;
                case 4:
                    str = (String) msg.obj;
                    btn_d.setText("sendMessageDelayed " + str);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casync_message);

        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        btn_c = findViewById(R.id.btn_c);
        btn_d = findViewById(R.id.btn_d);
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
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    public void btnOnClickB(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(2, 2 * 1000);
            }
        }).start();
    }

    public void btnOnClickC(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Message message = new Message();
                String data = "接口返回数据";
                message.what = 3;
                message.obj = data;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void btnOnClickD(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                String data = "接口返回数据";
                message.what = 4;
                message.obj = data;
                handler.sendMessageDelayed(message, 2 * 1000);
            }
        }).start();
    }
}