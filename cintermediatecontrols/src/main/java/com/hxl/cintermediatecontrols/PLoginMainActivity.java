package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PLoginMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private LinearLayout ll_password;
    private LinearLayout ll_verify_code;
    private CheckBox cb_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogin_main);

        final RadioGroup rg_login = findViewById(R.id.rg_login);
        ll_password = findViewById(R.id.ll_password);
        ll_verify_code = findViewById(R.id.ll_verify_code);
        cb_remember = findViewById(R.id.cb_remember);

        rg_login.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 密码登录
        if (checkedId == R.id.rb_password) {
            ll_password.setVisibility(View.VISIBLE);
            cb_remember.setVisibility(View.VISIBLE);
            ll_verify_code.setVisibility(View.GONE);
        }
        // 验证码登录
        else if (checkedId == R.id.rb_verify_code) {
            ll_password.setVisibility(View.GONE);
            cb_remember.setVisibility(View.GONE);
            ll_verify_code.setVisibility(View.VISIBLE);
        }
    }
}