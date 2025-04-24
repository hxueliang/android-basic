package com.hxl.gmmultithreading;

import android.util.Log;

public class AMyRunnable implements Runnable {
    @Override
    public void run() {
        Log.d("x_log", Thread.currentThread().getName() + ".run()");
    }
}
