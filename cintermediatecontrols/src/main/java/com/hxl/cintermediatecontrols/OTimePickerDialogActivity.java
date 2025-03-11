package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OTimePickerDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otime_picker_dialog);

        tv_message = findViewById(R.id.tv_message);
        findViewById(R.id.btn_show).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}