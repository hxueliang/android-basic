package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonLongClickListenerActivity extends AppCompatActivity {

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_long_click_listener);

        Button btn = findViewById(R.id.btn);
        tv_msg = findViewById(R.id.tv_msg);

        btn.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                tv_msg.setText("触发了长按事件");
                return false; // 自身不消耗事件，让事件继续冒泡
            }
        });

        // 匿名类语法糖 lambda表达式
        btn.setOnLongClickListener(v -> {
            tv_msg.setText("触发了长按事件");
            return false; // 自身不消耗事件，让事件继续冒泡
        });
    }
}