package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonEnableActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
    }
}