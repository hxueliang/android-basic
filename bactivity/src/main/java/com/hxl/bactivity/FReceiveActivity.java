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

        // 从上一个页面传来的意图中获取包裹
        Bundle bundle = getIntent().getExtras();
        String time = bundle.getString("time");
        String content = bundle.getString("content");
        String desc = String.format("收到消息：\n时间：%s\n消息：%s", time, content);
        tv_show_message.setText(desc);
    }
}