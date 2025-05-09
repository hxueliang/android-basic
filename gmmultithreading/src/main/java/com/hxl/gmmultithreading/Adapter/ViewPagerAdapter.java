package com.hxl.gmmultithreading.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hxl.gmmultithreading.AsyncTask.BitmapTask;
import com.hxl.gmmultithreading.R;

public class ViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final int[] datas;
    private final LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, int[] datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    // 渲染每一页数据
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View layout = layoutInflater.inflate(R.layout.j_viewpager_item, null);
        ImageView iv = layout.findViewById(R.id.iv_item);

        // 设置显示的图片
        // iv.setImageResource(datas[position]);
        // 异步任务加载图片
        BitmapTask bitmapTask = new BitmapTask(context, iv);
        bitmapTask.execute(datas[position]);

        container.addView(layout);

        return layout;
    }
}
