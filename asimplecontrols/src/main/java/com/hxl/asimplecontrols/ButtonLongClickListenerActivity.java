package com.hxl.asimplecontrols;

import android.os.Bundle;
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
    }
}