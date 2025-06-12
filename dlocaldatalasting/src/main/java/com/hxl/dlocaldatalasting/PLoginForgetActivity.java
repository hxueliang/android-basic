package com.hxl.dlocaldatalasting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PLoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private String mPhone;
    private String mVerifyCode = "";
    private EditText et_password_a;
    private EditText et_password_b;
    private EditText et_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogin_forget);

        // 获取从上一个页面传过来的phone参数
        mPhone = getIntent().getStringExtra("phone");

        et_password_a = findViewById(R.id.et_password_a);
        et_password_b = findViewById(R.id.et_password_b);
        et_verify = findViewById(R.id.et_verify);

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
            String p1 = et_password_a.getText().toString();
            String p2 = et_password_b.getText().toString();
            String verifyCode = et_verify.getText().toString();
            if (p1.length() < 6) {
                Toast.makeText(this, "输入的密码长度错误", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!p1.equals(p2)) {
                Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }

            if (mVerifyCode.equals("") || !mVerifyCode.equals(verifyCode)) {
                Toast.makeText(this, "输入的验证码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String msg = "密码修改成功";
            Log.d("x_log", msg);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            // 把修改好的新密码返回给上一个页面
            Intent intent = new Intent();
            intent.putExtra("new_password", p1);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}