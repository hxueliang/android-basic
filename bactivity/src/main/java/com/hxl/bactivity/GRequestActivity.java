package com.hxl.bactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.hxl.bactivity.util.util.DateUtil;

public class GRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_black;
    private TextView tv_show_result;
    private ActivityResultLauncher<Intent> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grequest);

        tv_black = findViewById(R.id.tv_black);
        tv_show_result = findViewById(R.id.tv_show_result);
        findViewById(R.id.btn_send).setOnClickListener(this);

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
            if (o == null) {
                return;
            }
            Intent intent = o.getData();
            if (intent == null) {
                return;
            }
            Bundle bundle = intent.getExtras();
            String time = bundle.getString("time");
            String content = bundle.getString("content");
            String desc = String.format("收到返回消息：\n时间：%s\n消息：%s", time, content);
            tv_show_result.setText(desc);
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, GResponseActivity.class);

        // 创建一个包裹对象
        Bundle bundle = new Bundle();
        bundle.putString("time", DateUtil.getNowTime());
        bundle.putString("content", tv_black.getText().toString());
        intent.putExtras(bundle);

        register.launch(intent);
    }
}