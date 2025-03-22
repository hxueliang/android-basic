package com.hxl.econtentprovider_client;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AContentWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private CheckBox ck_married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acontent_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_query_name).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        Boolean married = ck_married.isChecked();

        if (v.getId() == R.id.btn_add) {
        } else if (v.getId() == R.id.btn_delete) {
        } else if (v.getId() == R.id.btn_update) {
        } else if (v.getId() == R.id.btn_query) {
        } else if (v.getId() == R.id.btn_query_name) {
        }
    }
}