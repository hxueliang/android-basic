package com.hxl.econtentprovider_server.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.hxl.econtentprovider_server.database.AUserDBHelper;

// 快捷方式：包名目录处右键->new->Other->Content Provider
public class AUserInfoProvider extends ContentProvider {

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int USERS = 1;
    private static final int USER = 2;

    static {
        // 往Uri匹配器中添加指定的数据路径
        URI_MATCHER.addURI(AUserInfoContent.AUTHORITIES, "/user", USERS);
        URI_MATCHER.addURI(AUserInfoContent.AUTHORITIES, "/user/#", USER);
    }

    private AUserDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Log.d("x_log", "UserInfoProvider onCreate");
        dbHelper = AUserDBHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("x_log", "UserInfoProvider insert");
        // 当其它app访问的uri为以下uri时，就能访问到本Provider提供的数据
        // content://com.hxl.econtentprovider_server.Provider.UserInfoProvider/xxx
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        // db.insert(UserDBHelper.TABLE_NAME, null, values);
        if (URI_MATCHER.match(uri) == USERS) {
            // 强制 xxx 为 user
            // content://com.hxl.econtentprovider_server.Provider.UserInfoProvider/user
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert(AUserDBHelper.TABLE_NAME, null, values);
            /*
            Todo: 开启本段代码，记得注释第47行
            long rowId = db.insert(AUserDBHelper.TABLE_NAME, null, values);
            // 自己app的服务实现类似，短信通知服务功能
            if (rowId > 0) { // 判断插入是否执行成功
                // 如果添加成功，就利用新记录的行号生成新的地址
                Uri newUri = ContentUris.withAppendedId(AUserInfoContent.CONTENT_URI, rowId);
                // 通知监听器，数据已经改变
                getContext().getContentResolver().notifyChange(newUri, null);
            }
            */
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        if (URI_MATCHER.match(uri) == USERS) {
            /**
             * 删除多行
             * com.hxl.econtentprovider_server.Provider.UserInfoProvider/user
             */
            SQLiteDatabase db1 = dbHelper.getWritableDatabase();
            count = db1.delete(AUserDBHelper.TABLE_NAME, selection, selectionArgs);
            db1.close();
        } else if (URI_MATCHER.match(uri) == USER) {
            /**
             * 删除指定id的行
             * content://com.hxl.econtentprovider_server.Provider.UserInfoProvider/user/2
             */
            final String id = uri.getLastPathSegment();
            final SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            count = db2.delete(AUserDBHelper.TABLE_NAME, "_id=?", new String[]{id});
            db2.close();
        }
        return count;
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
        Cursor cursor = null;
        if (URI_MATCHER.match(uri) == USERS) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            cursor = db.query(AUserDBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}