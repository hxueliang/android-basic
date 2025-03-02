package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.asimplecontrols.util.DateUtil;

public class ButtonActivity extends AppCompatActivity {

    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        // 变量提取快捷键
        // mac: option + command + f
        // win: ctrl + alt + f
        tv_message = findViewById(R.id.tv_message);
    }

    public void handleClick1(View view) {
        // view 为 点当前点击的button
        String dateStr = DateUtil.getNowTime();
        String message = String.format(
                "时间：%s，控件：%s",
                dateStr,
                ((Button) view).getText() // 将 view 转为 Button 类型，才有 getText 方法
        );
        tv_message.setText(message);
    }
}