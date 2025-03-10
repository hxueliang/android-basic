package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class NTimePickerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;
    private TimePicker tp_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ntime_picker);

        tv_message = findViewById(R.id.tv_message);
        tp_time = findViewById(R.id.tp_time);
        tp_time.setIs24HourView(true);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String desc = String.format("%s时%s分", tp_time.getHour(), tp_time.getMinute());
        tv_message.setText(desc);
    }
}