package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.asimplecontrols.util.DateUtil;

public class ButtonClickListenerActivity extends AppCompatActivity {

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_listener);

        Button btn = findViewById(R.id.btn);
        tv_msg = findViewById(R.id.tv_msg);

        btn.setOnClickListener(
                // 创建MyOnClickListener实例，传入tv_msg，被MyOnClickListener方法接收
                new MyOnClickListener(tv_msg)
        );
    }

    static class MyOnClickListener implements View.OnClickListener {

        private final TextView tv_msg;

        public MyOnClickListener(TextView tvMsg) {
            this.tv_msg = tvMsg;
        }

        @Override
        public void onClick(View v) {
            String str = String.format("时间：%s，控件：%s", DateUtil.getNowTime(), ((Button) v).getText());
            this.tv_msg.setText(str);
        }
    }
}