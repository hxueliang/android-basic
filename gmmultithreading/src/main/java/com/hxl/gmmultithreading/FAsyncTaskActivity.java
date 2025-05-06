package com.hxl.gmmultithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FAsyncTaskActivity extends AppCompatActivity {

    private Button btn_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasync_task);

        btn_a = findViewById(R.id.btn_a);
    }

    public void btnOnClickA(View view) {
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, Void, String> {

        // 在异步线程中执行，即：子线程
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String str = "模拟网络返回数据";

            return str;
        }

        // 切换到主线程执行
        @Override
        protected void onPostExecute(String s) {
            btn_a.setText(s);
        }
    }
}