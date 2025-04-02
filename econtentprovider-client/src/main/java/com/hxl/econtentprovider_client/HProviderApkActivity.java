package com.hxl.econtentprovider_client;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HProviderApkActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hprovider_apk);

        findViewById(R.id.btn_install).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}