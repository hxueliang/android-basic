package com.hxl.bactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.bactivity.util.util.DateUtil;

public class GResponseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_show_message;
    private TextView tv_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gresponse);

        tv_show_message = findViewById(R.id.tv_show_message);
        tv_red = findViewById(R.id.tv_red);
        findViewById(R.id.btn_back).setOnClickListener(this);

        // 获取人上一个页面传过来的数据
        Bundle bundle = getIntent().getExtras();
        String time = bundle.getString("time");
        String content = bundle.getString("content");
        String desc = String.format("收到传来的消息：\n时间：%s\n消息：%s", time, content);
        tv_show_message.setText(desc);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        // 创建一个包裹对象
        Bundle bundle = new Bundle();
        bundle.putString("time", DateUtil.getNowTime());
        bundle.putString("content", tv_red.getText().toString());
        intent.putExtras(bundle);

        finish();
    }
}