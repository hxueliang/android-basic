package com.hxl.b4contentresolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amain);
    }

    /**
     * 通过ContentResolver调用ContentProvider插入一条记录
     *
     * @param v
     */
    public void onInsert(View v) {
    }

    /**
     * 通过ContentResolver调用ContentProvider删除一条记录
     *
     * @param v
     */
    public void onDelete(View v) {
    }

    /**
     * 通过ContentResolver调用ContentProvider更新一条记录
     *
     * @param v
     */
    public void onUpdate(View v) {
    }

    /**
     * 通过ContentResolver调用ContentProvider查询所有记录
     *
     * @param v
     */
    public void onQuery(View v) {
        // 得到ContentResolver对象
        final ContentResolver resolver = getContentResolver();
        // 调用其query，得到cursor
        final Uri uri = Uri.parse("content://com.hxl.b4contentprovider.provider.personprovider/person/1");
        final Cursor cursor = resolver.query(uri, null, null, null, null);
        // 取出cursor中的数据，并显示
        if (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Toast.makeText(this, id + " : " + name, Toast.LENGTH_LONG).show();
        }

    }
}