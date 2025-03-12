package com.hxl.dlocaldatalasting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BSharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private CheckBox ck_married;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bshare_references);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);
        findViewById(R.id.btn_save).setOnClickListener(this);

        preferences = getSharedPreferences("user_config", Context.MODE_PRIVATE);

        reload();
    }

    private void reload() {
        // Todo: 应该有权限问题，无法读取数据，启动应用闪退
        String name = preferences.getString("name", "");
        int age = preferences.getInt("age", 0);
        float height = preferences.getFloat("height", 0f);
        boolean married = preferences.getBoolean("married", false);

        if (name != "") {
            et_name.setText(name);
        }
        if (age != 0) {
            et_age.setText(String.valueOf(age));
        }
        if (height != 0f) {
            et_height.setText(String.valueOf(height));
        }
        ck_married.setChecked(married);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        Boolean married = ck_married.isChecked();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putInt("age", Integer.parseInt(age));
        editor.putFloat("name", Float.parseFloat(height));
        editor.putBoolean("married", married);
        editor.commit();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
}