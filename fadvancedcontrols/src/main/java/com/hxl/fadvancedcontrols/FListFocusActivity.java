package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.adapter.FPlanetListWithButtonAdapter;
import com.hxl.fadvancedcontrols.bean.DPlanet;
import com.hxl.fadvancedcontrols.util.ToastUtil;

import java.util.List;

public class FListFocusActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_planet;
    private List<DPlanet> dPlanetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flist_focus);
        dPlanetList = DPlanet.getDefaultList();
        lv_planet = findViewById(R.id.lv_planet);
        FPlanetListWithButtonAdapter adapter = new FPlanetListWithButtonAdapter(this, dPlanetList);
        lv_planet.setAdapter(adapter);
        lv_planet.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "你选择的是：" + dPlanetList.get(position).name);
    }
}