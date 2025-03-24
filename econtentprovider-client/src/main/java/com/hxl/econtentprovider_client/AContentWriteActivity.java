package com.hxl.econtentprovider_client;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.entity.User;
import com.hxl.econtentprovider_client.util.ToastUtil;

public class AContentWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private CheckBox ck_married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acontent_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_query_name).setOnClickListener(this);
    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        Boolean married = ck_married.isChecked();

        if (v.getId() == R.id.btn_add) {
            // 构建 values
            ContentValues values = new ContentValues();
            values.put(AUserInfoContent.USER_NAME, name);
            values.put(AUserInfoContent.USER_AGE, Integer.parseInt(age));
            values.put(AUserInfoContent.USER_HEIGHT, Float.parseFloat(height));
            values.put(AUserInfoContent.USER_MARRIED, married);
            // 执行添加
            getContentResolver().insert(AUserInfoContent.CONTENT_URI, values);
            // toast
            ToastUtil.show(this, "添加成功");
        } else if (v.getId() == R.id.btn_delete) {
            /**
             * 删除多行
             * com.hxl.econtentprovider_server.Provider.UserInfoProvider/user
             */
            int count = getContentResolver().delete(AUserInfoContent.CONTENT_URI, "name=?", new String[]{"hxl"});
            /**
             * 删除指定id的行
             * content://com.hxl.econtentprovider_server.Provider.UserInfoProvider/user/2
             */
            // Uri uri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI, 2);
            // int count = getContentResolver().delete(uri, null, null);
            if (count > 0) {
                ToastUtil.show(this, "删除成功");
            }
        } else if (v.getId() == R.id.btn_update) {
        } else if (v.getId() == R.id.btn_query) {
            Cursor cursor = getContentResolver().query(AUserInfoContent.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                // 构建 values
                while (cursor.moveToNext()) {
                    User info = new User();
                    info.id = cursor.getInt(cursor.getColumnIndex(AUserInfoContent._ID));
                    info.name = cursor.getString(cursor.getColumnIndex(AUserInfoContent.USER_NAME));
                    info.age = cursor.getInt(cursor.getColumnIndex(AUserInfoContent.USER_AGE));
                    info.height = cursor.getLong(cursor.getColumnIndex(AUserInfoContent.USER_HEIGHT));
                    info.married = cursor.getInt(cursor.getColumnIndex(AUserInfoContent.USER_MARRIED)) == 1;
                    Log.d("x_log", info.toString());
                }
                cursor.close();
            }
        } else if (v.getId() == R.id.btn_query_name) {
        }
    }
}