package com.hxl.gmmultithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class HProgressDemoActivity extends AppCompatActivity {

    private Button btn_a;
    private ProgressBar pb_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hprogress_demo);

        btn_a = findViewById(R.id.btn_a);
        pb_a = findViewById(R.id.pb_a);
    }

    public void btnOnClickA(View view) {
        new DownloadTask().execute();
    }

    class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
        int progress = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("x_log 1", "准备下载");
            pb_a.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Log.d("x_log 2", "正在下载");
            try {
                // 永久循环，模拟下载文件
                while (true) {
                    // 每隔一秒下载10%
                    Thread.sleep(1000);
                    progress += 10;
                    publishProgress(progress);
                    if (progress >= 100) {
                        break;
                    }
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("x_log 3", "已下载 " + values[0] + "%");
            pb_a.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            Log.d("x_log 4", "完成下载");

            if (b) {
                Log.d("x_log 5", "下载成功");
                pb_a.setVisibility(View.GONE);
            } else {
                Log.d("x_log 5", "下载失败");
            }
        }
    }
}