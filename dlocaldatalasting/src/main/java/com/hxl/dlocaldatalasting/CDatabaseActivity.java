package com.hxl.dlocaldatalasting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CDatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;
    private String mDatabaseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdatabase);

        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        tv_message = findViewById(R.id.tv_message);

        mDatabaseName = getFilesDir() + "/test.db";
    }

    @Override
    public void onClick(View v) {
        String desc = null;
        if (v.getId() == R.id.btn_create) {
            // 创建或打开数据库
            SQLiteDatabase db = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null);
            if (db != null) {
                desc = String.format("数据库存%s创建成功", db.getPath());
            } else {
                desc = "数据库存创建失败";
            }
            tv_message.setText(desc);
        } else if (v.getId() == R.id.btn_delete) {
            // 删除数据库
            boolean result = deleteDatabase(mDatabaseName);
            desc = String.format("数据库存%s删除%s", mDatabaseName, result ? "成功" : "失败");
            tv_message.setText(desc);
        }
    }
}