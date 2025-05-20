package com.hxl.b4contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hxl.b4contentprovider.db.DBHelper;

/**
 * 操作Person表的Provider类
 * <p>
 * 复制全类名：右键类名(PersonProvider)->Copy/Paste Special->Copy Reference
 * com.hxl.b4contentprovider.provider.PersonProvider
 */
public class PersonProvider extends ContentProvider {
    // 用来存放所有合法的Uri的容器
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * 保存一些合法的Uri
     * content://com.hxl.b4contentprovider.provider.personprovider/person   // 不根据id操作
     * content://com.hxl.b4contentprovider.provider.personprovider/person/3 // 根据id操作
     */
    static {
        matcher.addURI("com.hxl.b4contentprovider.provider.personprovider", "/person", 1);
        matcher.addURI("com.hxl.b4contentprovider.provider.personprovider", "/person/#", 2); // #：匹配任意数字
    }

    private DBHelper dBHelper;

    public PersonProvider() {
        // 在AndroidManifest.xml文件中，注册了该Provider，就会执行
        Log.d("x_log", "PersonProvider");
    }

    @Override
    public boolean onCreate() {
        // 在AndroidManifest.xml文件中，注册了该Provider，就会执行
        Log.d("x_log", "PersonProvider onCreate");
        dBHelper = new DBHelper(getContext());
        return false;
    }

    /**
     * content://com.hxl.b4contentprovider.provider.personprovider/person   // 不根据id查询
     * content://com.hxl.b4contentprovider.provider.personprovider/person/3 // 根据id查询
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("x_log", "PersonProvider query");

        Cursor cursor = null;

        // 得到链接对象
        SQLiteDatabase database = dBHelper.getReadableDatabase();

        // 匹配uri，返回code
        int code = matcher.match(uri);
        // 如果合法，进行查询
        if (code == 1) {
            // 不根据id查询
            cursor = database.query("person", projection, selection, selectionArgs, null, null, null);
        } else if (code == 2) {
            // 根据id查询
            final long id = ContentUris.parseId(uri);
            cursor = database.query("person", projection, "_id=?", new String[]{id + ""}, null, null, null);
        } else {
            // 如果不合法，抛出异常
            throw new RuntimeException("查询的uri不合法");
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d("x_log", "PersonProvider insert");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("x_log", "PersonProvider delete");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("x_log", "PersonProvider update");
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "";
    }
}
