package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.adapter.DPlanetBaseAdapter;
import com.hxl.fadvancedcontrols.bean.DPlanet;
import com.hxl.fadvancedcontrols.util.ToastUtil;

import java.util.List;

public class EListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<DPlanet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elist_view);

        final ListView lv_planet = findViewById(R.id.lv_planet);
        planetList = DPlanet.getDefaultList();
        DPlanetBaseAdapter adapter = new DPlanetBaseAdapter(this, planetList);
        lv_planet.setAdapter(adapter);

        lv_planet.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "你选择的是：" + planetList.get(position).name);
    }
}