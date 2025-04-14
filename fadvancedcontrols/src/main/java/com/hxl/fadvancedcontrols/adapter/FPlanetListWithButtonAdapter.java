package com.hxl.fadvancedcontrols.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxl.fadvancedcontrols.R;
import com.hxl.fadvancedcontrols.bean.DPlanet;
import com.hxl.fadvancedcontrols.util.ToastUtil;

import java.util.List;

public class FPlanetListWithButtonAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<DPlanet> mPlaneList;

    // 右键 -> Generate -> Constructor
    public FPlanetListWithButtonAdapter(Context mContext, List<DPlanet> mPlaneList) {
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
        ViewHolder holder;
        if (convertView == null) {
            // 根据布局文件item_list.xml生成转换视图对象
            convertView = LayoutInflater.from(mContext).inflate(R.layout.f_item_list_with_button, null);
            holder = new ViewHolder();
            holder.iv_icon = convertView.findViewById(R.id.iv_icon);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_desc = convertView.findViewById(R.id.tv_desc);
            holder.btn_click_me = convertView.findViewById(R.id.btn_click_me);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 控件设置数据
        final DPlanet dPlanet = mPlaneList.get(position);
        holder.iv_icon.setImageResource(dPlanet.image);
        holder.tv_name.setText(dPlanet.name);
        holder.tv_desc.setText(dPlanet.desc);
        holder.btn_click_me.setOnClickListener(v -> {
            ToastUtil.show(mContext, "按钮被点击了，" + dPlanet.name);
        });

        return convertView;
    }

    public final class ViewHolder {
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
        public Button btn_click_me;
    }
}
