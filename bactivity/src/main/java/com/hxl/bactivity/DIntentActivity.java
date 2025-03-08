package com.hxl.bactivity;

import android.content.ComponentName;
import android.content.Intent;
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
        // 1、在Intent的构造函数中指定
        // Intent intent = new Intent(this, AFinishActivity.class);

        // 2、调用意图对象的setClass方法指定
        // Intent intent = new Intent();
        // intent.setClass(this, AFinishActivity.class);

        // 3、调用意图对象的setComponent方法指定
        Intent intent = new Intent();
        ComponentName comp = new ComponentName(this, AFinishActivity.class);
        intent.setComponent(comp);

        startActivity(intent);
    }
}