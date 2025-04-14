package com.hxl.fadvancedcontrols.util;

import android.content.Context;

public class Utils {

    // 根据手机的分辨率把 dp 的单位转成为 px
    public static int dip2px(Context context, float dipValue) {
        // 获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        // 四舍五入
        return (int) (dipValue * scale + 0.5f);
    }

}
