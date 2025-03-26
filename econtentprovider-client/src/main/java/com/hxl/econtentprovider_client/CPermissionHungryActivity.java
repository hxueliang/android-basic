package com.hxl.econtentprovider_client;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CPermissionHungryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpermission_hungry);

        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
        } else if (v.getId() == R.id.btn_sms) {
        }
    }
}