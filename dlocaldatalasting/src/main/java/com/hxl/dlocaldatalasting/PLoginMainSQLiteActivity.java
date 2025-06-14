package com.hxl.dlocaldatalasting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.util.ViewUtil;

import java.util.Random;

public class PLoginMainSQLiteActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private String mPassword = "123456";
    private String mVerifyCode = "";
    private LinearLayout ll_password;
    private LinearLayout ll_verify_code;
    private CheckBox cb_remember;
    private EditText et_phone;
    private EditText et_password;
    private EditText et_verify;
    private Button btn_forget;
    private Button btn_verify;
    private Button btn_login;
    private RadioButton rb_password;
    private RadioButton rb_verify_code;
    private ActivityResultLauncher<Intent> register;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogin_main_sqlite);

        final RadioGroup rg_login = findViewById(R.id.rg_login);
        ll_password = findViewById(R.id.ll_password);
        ll_verify_code = findViewById(R.id.ll_verify_code);
        cb_remember = findViewById(R.id.cb_remember);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_verify = findViewById(R.id.et_verify);
        btn_forget = findViewById(R.id.btn_forget);
        btn_verify = findViewById(R.id.btn_verify);
        btn_login = findViewById(R.id.btn_login);
        rb_password = findViewById(R.id.rb_password);
        rb_verify_code = findViewById(R.id.rb_verify_code);

        btn_forget.setOnClickListener(this);
        btn_verify.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        rg_login.setOnCheckedChangeListener(this);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
        et_verify.addTextChangedListener(new HideTextWatcher(et_verify, 6));

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                Intent intent = o.getData();
                if (intent != null && o.getResultCode() == Activity.RESULT_OK) {
                    mPassword = intent.getStringExtra("new_password");
                }
            }
        });

        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        reLoad();
    }

    private void reLoad() {
        final boolean isRemember = preferences.getBoolean("isRemember", false);
        if (!isRemember) {
            return;
        }

        String phone = preferences.getString("phone", "");
        String password = preferences.getString("password", "");

        et_phone.setText(phone);
        et_password.setText(password);
        cb_remember.setChecked(true);
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

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        if (phone.length() < 11) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (v.getId() == R.id.btn_forget) {
            // 携带验证码跳转到忘记密码页
            Intent inter = new Intent(this, PLoginForgetActivity.class);
            inter.putExtra("phone", phone);
            register.launch(inter);
        } else if (v.getId() == R.id.btn_verify) {
            // 生成6位随机的验证码
            mVerifyCode = String.format("%06d", new Random().nextInt(999999));
            // 弹出提醒对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage(mVerifyCode);
            builder.setPositiveButton("ok", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (v.getId() == R.id.btn_login) {
            if (rb_password.isChecked()) {
                if (!mPassword.equals(et_password.getText().toString())) {
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                if (mVerifyCode.equals("") || !mVerifyCode.equals(et_verify.getText().toString())) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // 登录成功
            loginSuccess();
        }
    }

    private void loginSuccess() {
        setRememberData();

        // Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        String desc = String.format("您的手机号码是%s，恭喜你通过登录验证，点击'确定'按钮返回上个页面",
                et_phone.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回", (dialog, which) -> {
            finish();
        });
        builder.setNegativeButton("我再看看", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setRememberData() {
        String phone = "";
        String password = "";
        Boolean isRemember = false;
        if (cb_remember.isChecked()) {
            phone = et_phone.getText().toString();
            password = et_password.getText().toString();
            isRemember = cb_remember.isChecked();
        }

        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", phone);
        editor.putString("password", password);
        editor.putBoolean("isRemember", isRemember);
        editor.commit();
    }

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏软键盘
    private class HideTextWatcher implements TextWatcher {
        private final EditText mView;
        private final int mMaxLength;

        public HideTextWatcher(EditText v, int maxLength) {
            this.mView = v;
            this.mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == mMaxLength) {
                // 隐藏输入法，软键盘
                ViewUtil.hideOneInputMethod(PLoginMainSQLiteActivity.this, mView);
            }
        }
    }
}