package com.hxl.gmmultithreading;

import android.util.Log;

public class AMyThread extends Thread {
    @Override
    public void run() {
        Log.d("x_log", Thread.currentThread().getName() + ".run()");
    }
}
