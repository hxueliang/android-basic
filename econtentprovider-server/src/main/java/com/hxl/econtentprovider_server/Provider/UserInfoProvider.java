package com.hxl.econtentprovider_server.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.hxl.econtentprovider_server.database.UserDBHelper;

// 快捷方式：包名目录处右键->new->Other->Content Provider
public class UserInfoProvider extends ContentProvider {

    private UserDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Log.d("x_log", "UserInfoProvider onCreate");
        dbHelper = UserDBHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("x_log", "UserInfoProvider insert");
        // 当其它app访问的uri为以下uri时，就能访问到本Provider提供的数据
        // content://com.hxl.econtentprovider_server.Provider.UserInfoProvider/
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(UserDBHelper.TABLE_NAME, null, values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("x_log", "UserInfoProvider query");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(UserDBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}