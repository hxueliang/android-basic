package com.hxl.b4contentprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "atguigu.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("x_log", "onCreate()...");
        // 建表
        db.execSQL("create table perosn(_id integer primary key autoincrement, name varchar)");
        // 插入初始化数据
        db.execSQL("insert into person (name) values ('Tom')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
