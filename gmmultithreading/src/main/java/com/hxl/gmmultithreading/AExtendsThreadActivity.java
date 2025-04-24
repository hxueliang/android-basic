package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AExtendsThreadActivity extends AppCompatActivity {

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