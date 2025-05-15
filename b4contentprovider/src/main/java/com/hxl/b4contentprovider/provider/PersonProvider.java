package com.hxl.b4contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 操作Person表的Provider类
 * <p>
 * 复制全类名：右键类名(PersonProvider)->Copy/Paste Special->Copy Reference
 * com.hxl.b4contentprovider.provider.PersonProvider
 */
public class PersonProvider extends ContentProvider {
    public PersonProvider() {
        // 在AndroidManifest.xml文件中，注册了该Provider，就会执行
        Log.d("x_log", "PersonProvider");
    }

    @Override
    public boolean onCreate() {
        // 在AndroidManifest.xml文件中，注册了该Provider，就会执行
        Log.d("x_log", "PersonProvider onCreate");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("x_log", "PersonProvider query");
        return null;
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
