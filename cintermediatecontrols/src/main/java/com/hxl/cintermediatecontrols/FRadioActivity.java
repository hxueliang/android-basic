package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FRadioActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fradio);

        tv_result = findViewById(R.id.tv_result);
        RadioGroup rg_gender = findViewById(R.id.rg_gender);

        rg_gender.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_male) {
            String desc = String.format("您的性别是：%s", "男");
            tv_result.setText(desc);
        } else if (checkedId == R.id.rb_female) {
            String desc = String.format("您的性别是：%s", "女");
            tv_result.setText(desc);
        }
    }
}