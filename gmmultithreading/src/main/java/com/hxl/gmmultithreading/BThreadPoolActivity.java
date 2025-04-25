package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BThreadPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bthread_pool);
    }

    public void btnOnClick(View view) {
        testCached();
    }

    public void testCached() {
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("x_log", Thread.currentThread().getName() + " " + index);
                    /*
                        超短时间内并发，开启了6个线程处理10个任务
                        pool-3-thread-1 0
                        pool-3-thread-1 2
                        pool-3-thread-2 1
                        pool-3-thread-4 4
                        pool-3-thread-1 6
                        pool-3-thread-3 3
                        pool-3-thread-5 5
                        pool-3-thread-3 9
                        pool-3-thread-6 7
                        pool-3-thread-5 8
                     */
                }
            });
        }
    }
}