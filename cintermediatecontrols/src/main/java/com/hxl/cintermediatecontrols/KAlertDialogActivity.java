package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class KAlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalert_dialog);

        findViewById(R.id.btn_alert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}