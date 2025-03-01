package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.asimplecontrols.util.Utils;

public class ViewWidthHeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_width_height);

        TextView tv = findViewById(R.id.tv_width);
        // 获取 tv 的布局参数（含宽高单位为px）
        ViewGroup.LayoutParams params = tv.getLayoutParams();
        // 修改布局参数中的宽度值300dp，因为单位为px，需要把dp转成px
        params.width = Utils.dip2px(this, 300);
        // 设置 tv 的布局参数
        tv.setLayoutParams(params);
    }
}