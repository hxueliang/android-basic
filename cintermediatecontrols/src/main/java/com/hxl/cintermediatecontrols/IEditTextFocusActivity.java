package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IEditTextFocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText et_phone;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iedit_text_focus);

        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);

        et_pwd.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            String phone = et_phone.getText().toString();

            if (phone.isEmpty() || phone.length() < 11) {
                et_phone.requestFocus();
                Toast.makeText(this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
            }
        }
    }
}