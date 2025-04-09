package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.adapter.DPlanetBaseAdapter;
import com.hxl.fadvancedcontrols.bean.DPlanet;
import com.hxl.fadvancedcontrols.util.ToastUtil;

import java.util.List;

public class DBaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<DPlanet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbase_adapter);

        // 获取默认列表
        planetList = DPlanet.getDefaultList();
        // 构建适配器
        DPlanetBaseAdapter adapter = new DPlanetBaseAdapter(this, planetList);

        final Spinner sp_planet = findViewById(R.id.sp_planet);
        sp_planet.setAdapter(adapter);
        sp_planet.setSelection(0);
        sp_planet.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "您选择的是：" + planetList.get(position).name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}