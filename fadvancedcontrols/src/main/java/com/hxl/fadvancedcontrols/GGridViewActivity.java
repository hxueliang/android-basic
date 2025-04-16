package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.adapter.GPlanetGridAdapter;
import com.hxl.fadvancedcontrols.bean.DPlanet;
import com.hxl.fadvancedcontrols.util.ToastUtil;

import java.util.List;

public class GGridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gv_planet;
    private List<DPlanet> dPlanetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggrid_view);

        gv_planet = findViewById(R.id.gv_planet);
        dPlanetList = DPlanet.getDefaultList();
        final GPlanetGridAdapter gPlanetGridAdapter = new GPlanetGridAdapter(this, dPlanetList);
        gv_planet.setAdapter(gPlanetGridAdapter);

        gv_planet.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "选择的是：" + dPlanetList.get(position).name);
    }
}