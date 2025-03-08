package com.hxl.bactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.bactivity.util.util.DateUtil;

public class FSendActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsend);

        tv_message = findViewById(R.id.tv_message);
        findViewById(R.id.btn_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FReceiveActivity.class);

        // 创建一个包裹对象
        Bundle bundle = new Bundle();
        bundle.putString("time", DateUtil.getNowTime());
        bundle.putString("content", tv_message.getText().toString());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}