package com.hxl.econtentprovider_client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EMonitorSmsActivity extends AppCompatActivity {

    private SmsGetObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emonitor_sms);

        // 给指定Uri注册内容观察器，一旦发生数据变化，就触发观察器的onChange方法
        Uri uri = Uri.parse("Conent://sms");
        /**
         * notifyForDescendents:
         *      false: 表示精确匹配，即只匹配该Uri
         *      true: 表示可以同时匹配其派生的Uri
         * 假设 UriMatcher 里注册的 Uri 共有以下类型:
         *      content://AUTHORITIES/table
         *      content://AUTHORITIES/table/#
         *      content://AUTHORITIES/table/subtable
         * 假设我们当前需要观察的 Uri 为 content://AUTHORITIES/student:
         * 如果发生数据变化的 Uri 为 3。
         * 当 notifyForDescendents 为 false，那么该 ContentObserver 会监听不到，
         * 但是当 notifyForDescendents 为 true，那么该 ContentObserver 会监听到。
         */
        mObserver = new SmsGetObserver(this);
        getContentResolver().registerContentObserver(uri, true, mObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mObserver);
    }

    private static class SmsGetObserver extends ContentObserver {
        private final Context mContext;

        public SmsGetObserver(Context context) {
            super(new Handler(Looper.getMainLooper()));
            this.mContext = context;
        }

        @Override
        @SuppressLint("Range")
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);
            /**
             *  onChange 会多次调用，收到一条短信会调用两次 onChange
             *      mUri===content://sms/raw/20
             *      mUri===content://sms/inbox/20
             *  安卓7.0以上系统，点击标记为已读，也会调用一次
             *      mUri===content://sms
             *  收到一条短信都是uri后面都会有确定的一个数字，对应数据库的_id，比如上面的20
             */
            if (uri == null) {
                return;
            }
            if (
                    uri.toString().contains("content://sms/raw") ||
                            uri.toString().equals("content://sms")
            ) {
                return;
            }

            // 通过内容解析器获取符合条件的结果集游标
            final Cursor cursor = mContext.getContentResolver().query(uri, new String[]{"address", "body", "date"}, null, null, "date DESC");

            if (cursor.moveToNext()) {
                // 短信的发送号码
                final String sender = cursor.getString(cursor.getColumnIndex("address"));
                // 短信内容
                final String content = cursor.getString(cursor.getColumnIndex("body"));
                Log.d("x_log", String.format("sender:%s, content: %s", sender, content));
            }

            cursor.close();
        }
    }
}