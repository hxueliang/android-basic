package com.hxl.bactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IMetaDataActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_meta_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imeta_data);

        tv_meta_data = findViewById(R.id.tv_meta_data);
        findViewById(R.id.btn_get_meta).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}