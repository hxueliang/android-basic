package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JEditTextHideKeyboardActivity extends AppCompatActivity {

    private EditText et_phone;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jedit_text_hide_keyboard);

        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
    }
}