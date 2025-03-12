package com.hxl.dlocaldatalasting.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hxl.dlocaldatalasting.enity.User;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final String TABLE_NAME = "user_info";
    private static final int DB_VERSION = 1;
    private static UserDBHelper mHelper = null;
    private SQLiteDatabase mWDB = null;
    private SQLiteDatabase mRDB = null;

    private UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 利用单例模式获取数据库帮助器的唯一实例
    public static UserDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new UserDBHelper(context);
        }
        return mHelper;
    }

    // 打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    // 打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    // 关闭数据库连接
    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }
        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
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

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("age", user.age);
        values.put("height", user.height);
        values.put("married", user.married);

        // 执行插入记录动作，该语句返回插入记录的行号
        // 如果第三个参数values 为 Null 或者元素个数为0，由于 insert() 方法要求必须添加一条除了主键之外其它字段为 Null 值的记录，
        // 为了满足SQL语法的需要，insert 语句必须给定一个字段名，如：insert into person(name) values(NULL)，
        // 倘若不给定字段名，insert 语句就成了这样：insert into person() values()，显然这不满足标准SQL的语法。
        // 如果第三个参数 values 不为 Null 并且元素的个数大于0，可以把第二个参数设置为 null。
        return mWDB.insert(TABLE_NAME, null, values);
    }
}
