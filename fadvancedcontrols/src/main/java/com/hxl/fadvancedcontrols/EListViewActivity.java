package com.hxl.fadvancedcontrols;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.fadvancedcontrols.adapter.DPlanetBaseAdapter;
import com.hxl.fadvancedcontrols.bean.DPlanet;
import com.hxl.fadvancedcontrols.util.ToastUtil;
import com.hxl.fadvancedcontrols.util.Utils;

import java.util.List;

public class EListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private List<DPlanet> planetList;
    private CheckBox ck_selector;
    private CheckBox ck_divider;
    private ListView lv_planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elist_view);

        lv_planet = findViewById(R.id.lv_planet);
        planetList = DPlanet.getDefaultList();
        DPlanetBaseAdapter adapter = new DPlanetBaseAdapter(this, planetList);
        lv_planet.setAdapter(adapter);

        lv_planet.setOnItemClickListener(this);

        ck_divider = findViewById(R.id.ck_divider);
        ck_divider.setOnCheckedChangeListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "你选择的是：" + planetList.get(position).name);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.ck_divider) {
            // 显示分隔线
            if (ck_divider.isChecked()) {
                // 从资源文件获得图形对象
                Drawable drawable = getResources().getDrawable(R.color.black, getTheme());
                lv_planet.setDivider(drawable);
                // 设置列表视图的分隔线高度
                lv_planet.setDividerHeight(Utils.dip2px(this, 1));
            } else {
                lv_planet.setDivider(null);
                lv_planet.setDividerHeight(0);
            }
        }
    }
}