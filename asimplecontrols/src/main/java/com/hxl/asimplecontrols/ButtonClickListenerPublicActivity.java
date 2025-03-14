package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.asimplecontrols.util.DateUtil;

public class ButtonClickListenerPublicActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_listener_public);

        Button btn = findViewById(R.id.btn);
        Button btn2 = findViewById(R.id.btn2);
        tv_msg = findViewById(R.id.tv_msg);

        btn.setOnClickListener(
                // 传入当前类
                this
        );
        btn2.setOnClickListener(this);
    }

    // 实现 View.OnClickListener 接口
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn2) {
            tv_msg.setText("测试");
            return;
        }
        String str = String.format("时间：%s，控件：%s", DateUtil.getNowTime(), ((Button) v).getText());
        tv_msg.setText(str);
    }
}