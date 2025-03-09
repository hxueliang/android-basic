package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ESwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eswitch);

        Switch sw_status = findViewById(R.id.sw_status);
        tv_message = findViewById(R.id.tv_message);

        sw_status.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("开关的状态：%s", isChecked ? "开" : "关");
        tv_message.setText(desc);
    }
}