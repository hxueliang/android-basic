package com.hxl.econtentprovider_client;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FSendMmsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsend_mms);

        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_add) {
        }
    }
}