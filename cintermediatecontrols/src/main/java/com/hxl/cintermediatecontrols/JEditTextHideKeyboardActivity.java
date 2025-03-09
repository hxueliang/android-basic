package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.cintermediatecontrols.util.ViewUtil;

public class JEditTextHideKeyboardActivity extends AppCompatActivity {

    private EditText et_phone;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jedit_text_hide_keyboard);

        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);

        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        et_pwd.addTextChangedListener(new HideTextWatcher(et_pwd, 6));
    }

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        // 声明一个编辑框对象
        private final EditText mView;
        // 声明一个最大长度变量
        private final int mMaxLength;

        public HideTextWatcher(EditText v, int i) {
            this.mView = v;
            this.mMaxLength = i;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        // 在编辑框输入本文变化后触发
        @Override
        public void afterTextChanged(Editable s) {
            // 获取已输入的本文字符串
            String str = s.toString();
            if (str.length() == mMaxLength) {
                // 隐藏键盘
                ViewUtil.hideOneInputMethod(JEditTextHideKeyboardActivity.this, mView);
            }
        }
    }
}