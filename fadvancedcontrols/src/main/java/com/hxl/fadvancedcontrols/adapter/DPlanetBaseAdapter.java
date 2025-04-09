package com.hxl.fadvancedcontrols.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxl.fadvancedcontrols.R;
import com.hxl.fadvancedcontrols.bean.DPlanet;

import java.util.List;

public class DPlanetBaseAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<DPlanet> mPlaneList;

    // 右键 -> Generate -> Constructor
    public DPlanetBaseAdapter(Context mContext, List<DPlanet> mPlaneList) {
        this.mContext = mContext;
        this.mPlaneList = mPlaneList;
    }

    @Override
    public int getCount() {
        return mPlaneList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlaneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 根据布局文件item_list.xml生成转换视图对象
        final View v = LayoutInflater.from(mContext).inflate(R.layout.d_item_list, null);
        final ImageView iv_icon = v.findViewById(R.id.iv_icon);
        final TextView tv_name = v.findViewById(R.id.tv_name);
        final TextView tv_desc = v.findViewById(R.id.tv_desc);

        // 控件设置数据
        final DPlanet dPlanet = mPlaneList.get(position);
        iv_icon.setImageResource(dPlanet.image);
        tv_name.setText(dPlanet.name);
        tv_desc.setText(dPlanet.desc);

        return v;
    }
}
