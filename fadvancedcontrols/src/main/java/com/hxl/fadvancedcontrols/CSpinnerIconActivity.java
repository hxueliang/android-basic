package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CSpinnerIconActivity extends AppCompatActivity {

    private Spinner sp_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cspinner_icon);

        sp_icon = findViewById(R.id.sp_icon);
    }
}