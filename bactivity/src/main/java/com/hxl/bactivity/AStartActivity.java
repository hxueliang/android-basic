package com.hxl.bactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class AStartActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "x_log";

    @Override
    protected void onRestart() {
        // 当Activity从停止状态重新开始时调用。在调用onStart()之前调用。
        super.onRestart();
        Log.d(TAG, "AStartActivity onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 当Activity被创建时调用。在这个阶段，可以进行一些初始化操作，如设置布局、初始化变量等。
        super.onCreate(savedInstanceState);
        Log.d(TAG, "AStartActivity onCreate");
        setContentView(R.layout.activity_astart);

        findViewById(R.id.btn_go_finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AFinishActivity.class));
    }

    @Override
    protected void onStart() {
        // 当Activity变得可见时调用。此时，Activity已经准备好与用户进行交互，但其窗口尚未添加到窗口管理器中。
        super.onStart();
        Log.d(TAG, "AStartActivity onStart");
    }

    @Override
    protected void onResume() {
        // 当Activity开始与用户交互时调用。此时，Activity处于前台，并且拥有用户焦点。
        super.onResume();
        Log.d(TAG, "AStartActivity onResume");
    }

    @Override
    protected void onPause() {
        // 当系统准备开始另一个Activity（当前Activity即将失去焦点）时调用。在这个阶段，可以保存一些临时数据，但不应该进行耗时操作，如数据库写入操作。
        super.onPause();
        Log.d(TAG, "AStartActivity onPause");
    }

    @Override
    protected void onStop() {
        // 当Activity不再可见时调用。此时，用户可能已经离开了这个Activity，但该Activity仍然保留在内存中。
        super.onStop();
        Log.d(TAG, "AStartActivity onStop");
    }

    @Override
    protected void onDestroy() {
        // 当Activity被销毁时调用。此时，应该释放掉所有的资源，如关闭数据库连接、停止服务等。
        super.onDestroy();
        Log.d(TAG, "AStartActivity onDestroy");
    }
}