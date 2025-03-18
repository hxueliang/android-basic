package com.hxl.dlocaldatalasting;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

public class GMyApplication extends Application {

    // 在App启动时调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("x_log", "Application onCreate");
    }

    // 在App终止时调用
    // 该方法可用于模拟的流程环境。
    // 它永远不会在Android生产设备上被调用，
    // 因为在生产设备上，进程会被简单地杀死；
    // 这样做时不会执行用户代码（包括这个回调函数）
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("x_log", "Application onTerminate");
    }

    // 在配置改变时调用，例如从竖屏变为横屏。
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("x_log", "Application onConfigurationChanged");
    }
}
