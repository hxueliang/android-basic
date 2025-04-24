package com.hxl.gmmultithreading;

import android.util.Log;

public class ASaleTicket implements Runnable {
    private final int total = 30; // 总票数
    private int ticket = 30; // 剩余的票数

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (ticket > 0) {
                    Log.d("x_log", Thread.currentThread().getName() + "卖出第" + (total - ticket + 1) + "张票");
                    ticket--;
                } else {
                    Log.d("x_log", "火车票卖完了");
                    break;
                }
            }
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
