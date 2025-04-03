package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.util.ToastUtil;

public class BSpinnerDialogActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final static String[] startArray = {"金星", "木星", "水星", "火星", "土星"};
    private Spinner sp_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bspinner_dialog);

        sp_dialog = findViewById(R.id.sp_dialog);

        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> startAdapter = new ArrayAdapter<>(this, R.layout.b_item_select, startArray);
        sp_dialog.setAdapter(startAdapter);
        // 设置下拉框默认显示第一项
        sp_dialog.setSelection(0);
        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的 onItemSelected 方法
        sp_dialog.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "您选择的是：" + startArray[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}