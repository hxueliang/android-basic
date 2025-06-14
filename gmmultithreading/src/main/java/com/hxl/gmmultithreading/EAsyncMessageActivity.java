package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EAsyncMessageActivity extends AppCompatActivity {

    /**
     * 异步消息处理机制总结：
     * 1. 传递Message。用于接受子线程发送的数据，并用此数据配合主线程更新UI。
     * 在Android中，对于UI的操作通常需要放在主线程中进行操作。如果在子线程中有关于UI的操作，
     * 那么就需要把数据消息作为一个Message对象发送到消息队列中，
     * 然后，用Handler中的handlerMessage方法处理传过来的数据信息，并操作UI。
     * 类sendMessage(Message msg)方法实现传过来的数据信息的操作。
     * 在初始化Handler对象时重写的handleMessage方法来接收Message并进行相关操作。
     * 2. 传递Runnable对象。用于通过Handler绑定的消息队列，安排不同操作的执行顺序。
     * Handler对象在进行初始化的时候，会默认的自动绑定消息队列。
     * 利用类post方法，可以将Runnable对象发送到消息队列中，
     * 按照队列的机制按顺序执行不同的Runnable对象中的run方法。
     */

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
            }
        }
    };
    private Button btn_b;
    private Button btn_d;
    private Button btn_e;
    private Button btn_f;
    private Button btn_g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easync_message);

        btn_b = findViewById(R.id.btn_b);
        btn_d = findViewById(R.id.btn_d);
        btn_e = findViewById(R.id.btn_e);
        btn_f = findViewById(R.id.btn_f);
        btn_g = findViewById(R.id.btn_g);
    }

    public void btnOnClickA(View view) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("x_log", "我在主线程：handler post");
            }
        });
    }

    public void btnOnClickB(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btn_b.setText("post发出的修改UI事件");
                    }
                });
            }
        }).start();
    }

    public void btnOnClickC(View view) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("x_log", "我在主线程：handler postDelayed");
            }
        }, 2 * 1000);
    }

    public void btnOnClickD(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_d.setText("postDelayed 发出的修改UI事件");
                    }
                }, 2 * 1000);
            }
        }).start();
    }

    public void btnOnClickE(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                /*
                    为什么View.post、postDelayed可以实现类似于Handle的效果呢
                    下面是post方法的原码

                    public boolean post(Runnable action) {
                        Handler handler;
                        if (mAttachInfo != null) {
                            // 取当前UI线程里，自带的handler，赋值给局部的handler
                            handler = mAttachInfo.mHandler;
                        } else {
                            ViewRoot.getRunQueue().post(action)
                            return true;
                        }
                        // 使用局部的handler的post方法。即：View.post本质还是handler.post
                        return handler.post(action);
                    }
                 */
                btn_e.post(new Runnable() {
                    @Override
                    public void run() {
                        btn_e.setText("通过View.post 修改UI");
                    }
                });
            }
        }).start();
    }

    public void btnOnClickF(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                btn_f.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_f.setText("通过View.postDelayed 修改UI");
                    }
                }, 2 * 1000);
            }
        }).start();
    }

    public void btnOnClickG(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                    runOnUiThread方法的原理
                    源码

                    public final void runOnUiThread(Runnable action) {
                        if (Thread.currentThread() != mUiThread) {
                            mHandler.post(action);
                        } else {
                            action.run();
                        }
                    }

                    可以看出其本质也是handler.post
                 */
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_g.setText("通过runOnUiThread 修改UI");
                    }
                });
            }
        }).start();
    }
}