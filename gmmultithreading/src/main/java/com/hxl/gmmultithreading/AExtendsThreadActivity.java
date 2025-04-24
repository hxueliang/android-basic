package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AExtendsThreadActivity extends AppCompatActivity {
    /**
     * 实现Runnable和继承Thread区别
     * 1. 一个类只能继承一个父类，存在局限；一个类可以实现多个接口；
     * 2. 在实现Runnable接口的时候用Thread(Runnable target)创建进程时，
     * 使用同一个Runnable实例，则建立的建立的多线程的实例变量也是共享的。
     * 但是通过继承Thread类是不能用一个实例建立多个线程， 故而实现Runnable接口适合于资源共享。
     * 当然，继承Thread类也能够共享变量，能共享Thread类的static变量(通常不会这么做)；
     * 3. Runnable接口和Thread之间的联系：
     * public class Thread extends Object implements Runnable
     * 可以看出Thread类也是Runnable接口的子类
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aextends_thread);
    }

    public void btnOnClick(View view) {
        testThread();
    }

    public void testThread() {
        AMyThread mt1 = new AMyThread();
        AMyThread mt2 = new AMyThread();
        mt1.start(); // Thread-2.run()
        mt2.start(); // Thread-3.run()
    }

    public void btnOnClickB(View view) {
        testThreadB();
    }

    public void testThreadB() {
        AMyRunnable mr1 = new AMyRunnable();
        AMyRunnable mr2 = new AMyRunnable();
        Thread t1 = new Thread(mr1);
        Thread t2 = new Thread(mr2);
        t1.start(); // Thread-4.run()
        t2.start(); // Thread-5.run()
    }

    public void btnOnClickC(View view) {
        testThreadC();
    }

    public void testThreadC() {
        ASaleTicket saleTicket = new ASaleTicket();
        Thread t1 = new Thread(saleTicket, "A代理");
        Thread t2 = new Thread(saleTicket, "B代理");
        Thread t3 = new Thread(saleTicket, "C代理");
        Thread t4 = new Thread(saleTicket, "D代理");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}