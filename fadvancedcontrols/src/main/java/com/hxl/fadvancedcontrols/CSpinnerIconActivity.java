package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // 定义下拉列表需要显示的行星图标数组
    private static final int[] iconArray = {
            R.drawable.ic_boy, R.drawable.ic_girl, R.drawable.yellow, R.drawable.green, R.drawable.blue
    };
    // 定义下列表需要显示的行星名称数组
    private static final String[] startArray = {"地球", "火星", "土星", "木星", "水星"};
    private Spinner sp_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cspinner_icon);

        // 声明一个映射对象的列表，用于保存行星的图标与名称配对信息
        List<Map<String, Object>> list = new ArrayList<>();
        // iconArray是行星的图标数组，starArray是行星的名称数组
        for (int i = 0; i < iconArray.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("icon", iconArray[i]);
            item.put("name", startArray[i]);
            list.add(item);
        }
        SimpleAdapter startAdapter = new SimpleAdapter(
                this,
                list,
                R.layout.c_item_simple,
                new String[]{"icon", "name"},
                new int[]{R.id.iv_icon, R.id.tv_name}
        );

        sp_icon = findViewById(R.id.sp_icon);
        sp_icon.setAdapter(startAdapter);
        sp_icon.setSelection(0);
        sp_icon.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "您选择的是：" + startArray[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}