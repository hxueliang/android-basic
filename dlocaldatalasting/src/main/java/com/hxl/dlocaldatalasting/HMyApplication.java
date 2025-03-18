package com.hxl.dlocaldatalasting;

import android.app.Application;

import java.util.HashMap;

public class HMyApplication extends Application {

    /**
     * 适合在Application中保存的全局变量主要有3类数据：
     * 1. 会频繁读取的信息，如用户名、手机号等
     * 2. 不方便由意图传递的数据，如位图对象、非字符串型的集合对象等
     * 3. 容易因频繁分配内存而导致内存泄漏的对象，如Handler对象等
     */

    // 声明一个变量，用于储存当前类实例
    private static HMyApplication mApp;

    // 声明一个公共的信息映射对象，可当作全局变量使用
    public HashMap<String, String> infoMap = new HashMap<>();

    // 获取类实例
    public static HMyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        mApp = this;
    }
}
