package com.hxl.econtentprovider_server.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AUserDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "user_info";
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    private static AUserDBHelper mHelper = null;

    private AUserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 利用单例模式获取数据库帮助器的唯一实例
    public static AUserDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new AUserDBHelper(context);
        }
        return mHelper;
    }

    // 创建数据库，执行SQL语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " age INTEGER NOT NULL," +
                " height LONG NOT NULL," +
                " married INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
