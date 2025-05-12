package com.hxl.gmmultithreading;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.hxl.gmmultithreading.Adapter.ViewPagerAdapter;
import com.hxl.gmmultithreading.util.Images;

public class JImagePlayDemoActivity extends AppCompatActivity {

    // 实现无限滑动(方案二:构造数据源)-1.构造数据源和长度为原来的加2
    private final int[] datas = new int[Images.imageArray.length + 2];
    private ViewPager vp;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jimage_play_demo);

        vp = findViewById(R.id.vp);

        // 实现无限滑动(方案二:构造数据源)-3.初始化数据
        initDatas();
        // 初始化适配器
        // viewPagerAdapter = new ViewPagerAdapter(this, Images.imageArray);
        // 实现无限滑动(方案二:构造数据源)-4.初始化适配器
        viewPagerAdapter = new ViewPagerAdapter(this, datas);
        // 绑定适配器
        vp.setAdapter(viewPagerAdapter);
        // 实现无限滑动(方案一:欺骗适配器)-3.选择一个较大的条目选中，目的是为了可以往左不断滑动
        // vp.setCurrentItem(Images.imageArray.length * 100000000);
        // 实现无限滑动(方案二:构造数据源)-9.绑定监听器
        vp.addOnPageChangeListener(new ViewPagerChangeListener());
        // 实现无限滑动(方案二:构造数据源)-5.默认选中第二个张图片
        vp.setCurrentItem(1, false);
    }

    // 实现无限滑动(方案二:构造数据源)-2.为构造数据源赋值
    private void initDatas() {
        datas[0] = Images.imageArray[Images.imageArray.length - 1];
        // for (int i = 0; i < Images.imageArray.length; i++) {
        //     datas[i + 1] = Images.imageArray[i];
        // }
        // 上面for循环的语法糖
        System.arraycopy(Images.imageArray, 0, datas, 1, Images.imageArray.length);
        datas[datas.length - 1] = Images.imageArray[0];
    }

    // 实现无限滑动(方案二:构造数据源)-8.创建监听器，监听滑动
    class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {
        int pageIndex; // 当前应用显示的页面索引

        @Override
        public void onPageSelected(int position) {
            pageIndex = position;
            if (position == 0) {
                // 把 pageIndex 设置为数据源的倒数第二张
                pageIndex = datas.length - 2;
            } else if (position == datas.length - 1) {
                // 把 pageIndex 设置为数据源的第二张
                pageIndex = 1;
            }
            if (position != pageIndex) {
                // 如何两者不相等，说明position是第一张或最后一张
                // 侧快速替换为目标对象
                vp.setCurrentItem(pageIndex, false);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}