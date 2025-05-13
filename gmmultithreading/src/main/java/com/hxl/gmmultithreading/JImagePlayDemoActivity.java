package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.hxl.gmmultithreading.Adapter.ViewPagerAdapter;
import com.hxl.gmmultithreading.util.Images;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JImagePlayDemoActivity extends AppCompatActivity {

    private ViewPager vp;
    private ViewPagerAdapter viewPagerAdapter;
    private ScheduledExecutorService scheduledExecutorService; // 自动轮播定时器
    private int currentIndex; // 当前图片的索引号
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    // 刷新控件，选中对应的图片
                    vp.setCurrentItem(currentIndex);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jimage_play_demo);

        vp = findViewById(R.id.vp);

        // 初始化适配器
        viewPagerAdapter = new ViewPagerAdapter(this, Images.imageArray);
        // 绑定适配器
        vp.setAdapter(viewPagerAdapter);
        vp.addOnPageChangeListener(new ViewPagerChangeListener());
        // 实现无限滑动(方案一:欺骗适配器)-3.选择一个较大的条目选中，目的是为了可以往左不断滑动
        currentIndex = Images.imageArray.length * 100000000;
        vp.setCurrentItem(currentIndex, true);
    }

    // 当界面可见的时候，每隔10秒切换图片
    @Override
    protected void onStart() {
        super.onStart();
        // 初始化自动轮播定时器
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 切换图片
                currentIndex++;
                handler.sendEmptyMessage(1);
            }
        }, 4, 4, TimeUnit.SECONDS);
    }

    // 当界面不可见的时候
    @Override
    protected void onStop() {
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }

    // 创建监听器，监听滑动
    class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            currentIndex = position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}