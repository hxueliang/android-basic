package com.hxl.gmmultithreading;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.hxl.gmmultithreading.Adapter.ViewPagerAdapter;
import com.hxl.gmmultithreading.util.Images;

public class JImagePlayDemoActivity extends AppCompatActivity {

    private ViewPager vp;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jimage_play_demo);

        vp = findViewById(R.id.vp);

        // 初始化适配器
        viewPagerAdapter = new ViewPagerAdapter(this, Images.imageArray);
        // 绑定适配器
        vp.setAdapter(viewPagerAdapter);
        // 实现无限滑动(方案一:欺骗适配器)-3.选择一个较大的条目选中，目的是为了可以往左不断滑动
        vp.setCurrentItem(Images.imageArray.length * 100000000);
    }
}