package com.hxl.bactivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DIntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dintent);

        findViewById(R.id.btn_explicit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        
    }
}