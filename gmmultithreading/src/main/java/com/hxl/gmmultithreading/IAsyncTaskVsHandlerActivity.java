package com.hxl.gmmultithreading;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IAsyncTaskVsHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iasync_task_vs_handler);

        final TextView tv_a = findViewById(R.id.tv_a);

        /**
         * AsyncTask 和 Handler 对比
         *
         * AsyncTask
         * 原理：
         *  AsyncTask，是android提供的轻量级的异步类，可以直接继承AsyncTask，在类中实现异步操作，
         *  并提供接口返馈当前异步执行的程度（可以通过接口实现UI进度更新），最后反馈执行的结果给UI主线程。
         * 优点：
         *  简单，快捷，过程可控
         * 缺点：
         *  在使用多个异步操作和并需要进行UI变更时，就变得复杂起来
         *
         * Handler
         * 原理：
         *  在Handler异步实现时，涉及到Handler，Looper，Message，Thread四个对象，
         *  实现异步的流程是主线程启动Thread（子线程），子线程运行并生成Message通过Handler发送出去，
         *  然后Looper取出消息队列中的Message再分发给Handler进行UI变更。
         * 优点：
         *  结构清晰，功能定义明确。对于多个后台任务时，简单，清晰
         * 缺点：
         *  在单个后台异步处理时，显得代码过多，结构过于复杂（相对性）
         */
    }
}