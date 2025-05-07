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

    /**
     * 第一个泛型参数指定为Void，表示在执行AsyncTask的时候不需要传入参数给后台任务。
     * 第二个泛型参数指定为Integer，表示使用整形数据来作为进度显示单位。
     * 第三个泛型参数指定为String，则表示使用字符串型数据来返馈执行结果
     * <p>
     * 当然，目前我们自定义的MyTask还是一个空任务，并不能进行任务，并不能进行任何实际的操作，
     * 我们还需要去重写AsyncTask中的几个方法才能完成对任务的定制。经常需要去重写的方法有以下四个：
     */
    class MyTask extends AsyncTask<Void, Integer, String> {
        /**
         * 会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作
         * 比如显示一个进度条对话框等
         * 【主线程】
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("x_log 1", "onPreExecute");
        }

        /**
         * 这个方法中所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。
         * 任务一旦完成就可以通过return语句来将任务的执行结果进行返回。
         * 如果AsyncTask的第三个泛型参数指定的是Void，就可以不返回任务执行结果。
         * 注意，在这个方法中不可以进行UI操作，
         * 如果需要更新UI元素，比如当前任务进度，调用publishProgress(Progress...)
         * 【子线程】
         *
         * @param voids The parameters of the task.
         * @return
         */
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

        /**
         * 当在后台任务中调用了publishProgress(Progress...)方法后，
         * 这个方法就很快会被调用，方法中携带的参数就是在后台任务中传递过来的。
         * 在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新。
         * 【主线程】
         *
         * @param values The values indicating progress.
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("x_log 3", "onProgressUpdate " + values);
        }

        /**
         * 当后台任务执行完毕并通过return语句返回时，这个方法就很快会被调用。
         * 返回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些UI操作，
         * 比如提醒任务已经完成
         * 【主线程】
         *
         * @param s The result of the operation computed by {@link #doInBackground}.
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("x_log 4", "onPostExecute " + s);
        }
    }
}