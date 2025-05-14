package com.hxl.b4contentresolver;

import android.os.Bundle;
import android.view.View;

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
    }
}