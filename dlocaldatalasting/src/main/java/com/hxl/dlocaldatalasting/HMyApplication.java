package com.hxl.dlocaldatalasting;

import android.app.Application;

import java.util.HashMap;

public class HMyApplication extends Application {

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
