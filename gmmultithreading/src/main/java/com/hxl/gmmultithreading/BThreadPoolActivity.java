package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BThreadPoolActivity extends AppCompatActivity {

    private ScheduledExecutorService singleThreadScheduledExecutorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bthread_pool);
    }

    public void btnOnClick(View view) {
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int index = i;

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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

                    /*
                        newCachedThreadPool创建一个可绑缓存线程池，
                        如果线程池长度超过处理需要，可灵活回收空闲线程，
                        若无可回收，则新建线程。
                     */

                    /**
                     * 执行一个任务休息2秒，Thread.sleep(2 * 1000);
                     * 线程执行完上一次任务，被回收
                     * 再执行下一次任务时，可以重复使用之前的线程，
                     * 从而测试出非高并发的情况下，重复复用了这前的线程，不创建新线程。
                     *
                     * pool-3-thread-1 0
                     * pool-3-thread-1 1
                     * pool-3-thread-1 2
                     * pool-3-thread-1 3
                     * pool-3-thread-1 4
                     * pool-3-thread-1 5
                     * pool-3-thread-1 6
                     * pool-3-thread-1 7
                     * pool-3-thread-1 8
                     * pool-3-thread-1 9
                     *
                     */
                }
            });
        }
    }

    public void btnOnClickFixed(View view) {
        /**
         * newFixedThreadPool 创建一个定长线程池，可以控制线程最大并发数，超出的线程会在队列中等待。
         */
        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("x_log", Thread.currentThread().getName() + " " + index);
                    /*
                        高并发，线程池是最多只有3个线程
                        pool-5-thread-2 1
                        pool-5-thread-1 0
                        pool-5-thread-3 2
                        pool-5-thread-3 3
                        pool-5-thread-3 4
                        pool-5-thread-3 5
                        pool-5-thread-1 7
                        pool-5-thread-3 8
                        pool-5-thread-3 9
                        pool-5-thread-2 6
                     */

                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    /*
                        测试，每个线程增加2秒的休眠时间
                        pool-3-thread-2 1
                        pool-3-thread-1 0
                        pool-3-thread-3 2
                        // 停2秒
                        pool-3-thread-1 3
                        pool-3-thread-2 4
                        pool-3-thread-3 5
                        // 停2秒
                        pool-3-thread-1 6
                        pool-3-thread-2 7
                        pool-3-thread-3 8
                        // 停2秒
                        pool-3-thread-1 9
                     */
                }
            });
        }
    }

    public void btnOnClickSingle(View view) {
        /**
         * newSingleTreadExecutor 创建一个单线程化的线程池，
         * 它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行
         */
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            int index = i;

            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("x_log", Thread.currentThread().getName() + " " + index);
                    /**
                     * 高并发场景下，也只会创建一个线程执行
                     *
                     * pool-3-thread-1 0
                     * pool-3-thread-1 1
                     * pool-3-thread-1 2
                     * pool-3-thread-1 3
                     * pool-3-thread-1 4
                     * pool-3-thread-1 5
                     * pool-3-thread-1 6
                     * pool-3-thread-1 7
                     * pool-3-thread-1 8
                     * pool-3-thread-1 9
                     */

                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    /**
                     * 测试，线程增加2秒的休眠时间
                     *
                     * pool-3-thread-1 0
                     // 停2秒
                     * pool-3-thread-1 1
                     // 停2秒
                     * pool-3-thread-1 2
                     // 停2秒
                     * pool-3-thread-1 3
                     // 停2秒
                     * pool-3-thread-1 4
                     // 停2秒
                     * pool-3-thread-1 5
                     // 停2秒
                     * pool-3-thread-1 6
                     // 停2秒
                     * pool-3-thread-1 7
                     // 停2秒
                     * pool-3-thread-1 8
                     // 停2秒
                     * pool-3-thread-1 9
                     */
                }
            });
        }
    }

    public void btnOnClickTiming(View view) {
        Log.d("x_log", "start");
        final ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        // 定时3秒以后执行
        singleThreadScheduledExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                Log.d("x_log", "延迟3秒执行");
            }
        }, 3, TimeUnit.SECONDS);
    }

    public void btnOnClickPeriod(View view) {
        Log.d("x_log", "start");
        singleThreadScheduledExecutorB = Executors.newSingleThreadScheduledExecutor();
        // 定时2秒以后执执行，每隔3秒执行一次
        singleThreadScheduledExecutorB.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Log.d("x_log", "定时2秒以后执执行，每隔3秒执行一次");
            }
        }, 2, 3, TimeUnit.SECONDS);
    }

    public void btnOnClickStopPeriod(View view) {
        singleThreadScheduledExecutorB.shutdown();
        Log.d("x_log", "stop");
    }
}