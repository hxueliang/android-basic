package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.asimplecontrols.util.DateUtil;

public class ButtonClickListenerAnonymousActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_listener_anonymous);
        Button btn = findViewById(R.id.btn);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        tv_msg = findViewById(R.id.tv_msg);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv_msg.setText("使用匿名类实现接口");
            }
        });
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