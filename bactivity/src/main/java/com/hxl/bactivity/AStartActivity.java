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
        super.onRestart();
        Log.d(TAG, "AStartActivity onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        super.onStart();
        Log.d(TAG, "AStartActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "AStartActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "AStartActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "AStartActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "AStartActivity onDestroy");
    }
}