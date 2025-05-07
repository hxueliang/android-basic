package com.hxl.gmmultithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GAsyncTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasync_task);
    }

    public void btnOnClickA(View view) {
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("x_log 1", "onPreExecute");
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("x_log 2", "doInBackground");
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            publishProgress(99);
            String str = "模拟接口返回数据";
            return str;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("x_log 3", "onProgressUpdate " + values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("x_log 4", "onPostExecute " + s);
        }
    }
}