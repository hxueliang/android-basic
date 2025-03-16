package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fimage_write);

        iv_content = findViewById(R.id.iv_content);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
        } else if (v.getId() == R.id.btn_read) {
        }
    }
}