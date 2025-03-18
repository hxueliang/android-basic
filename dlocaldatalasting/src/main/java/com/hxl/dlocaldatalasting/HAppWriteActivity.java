package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.util.ToastUtil;

public class HAppWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private CheckBox ck_married;
    private HMyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happ_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);
        findViewById(R.id.btn_save).setOnClickListener(this);

        app = HMyApplication.getInstance();

        reload();
    }

    private void reload() {
        String name = app.infoMap.get("name");
        String age = app.infoMap.get("age");
        String height = app.infoMap.get("height");
        String married = app.infoMap.get("married");

        if (name == null) {
            return;
        }
        et_name.setText(name);
        et_age.setText(age);
        et_height.setText(height);
        ck_married.setChecked(married.equals("是"));
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        Boolean married = ck_married.isChecked();

        app.infoMap.put("name", name);
        app.infoMap.put("age", age);
        app.infoMap.put("height", height);
        app.infoMap.put("married", married ? "是" : "否");
        ToastUtil.show(this, "保存成功");
    }
}