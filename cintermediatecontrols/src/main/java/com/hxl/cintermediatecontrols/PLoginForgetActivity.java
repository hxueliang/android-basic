package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PLoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private String mPhone;
    private String mVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogin_forget);

        // 获取从上一个页面传过来的phone参数
        mPhone = getIntent().getStringExtra("phone");

        findViewById(R.id.btn_verify).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_verify) {
            // 生成6位随机的验证码
            mVerifyCode = String.format("%06d", new Random().nextInt(999999));
            // 弹出提醒对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号：" + mPhone + "，本次验证码：" + mVerifyCode);
            builder.setPositiveButton("ok", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (v.getId() == R.id.btn_confirm) {
            // 点击确认按钮
        }
    }
}