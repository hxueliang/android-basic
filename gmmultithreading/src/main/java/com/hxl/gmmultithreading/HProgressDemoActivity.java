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

    class DownloadTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("x_log 1", "准备下载");
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("x_log 2", "正在下载");
            return "";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.d("x_log 3", "更新下载");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("x_log 4", "完成下载");
        }
    }
}