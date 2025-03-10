package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LDatePickerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;
    private DatePicker dp_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldate_picker);

        tv_message = findViewById(R.id.tv_message);
        dp_date = findViewById(R.id.dp_date);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String desc = String.format("%s年%s月%s日", dp_date.getYear(), dp_date.getMonth() + 1, dp_date.getDayOfMonth());
        tv_message.setText(desc);
    }
}