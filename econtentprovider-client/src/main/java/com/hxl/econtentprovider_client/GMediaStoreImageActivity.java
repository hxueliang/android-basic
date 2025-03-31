package com.hxl.econtentprovider_client;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class GMediaStoreImageActivity extends AppCompatActivity {

    private final List<ImageInfo> mImageList = new ArrayList<>();
    private TextView et_phone;
    private TextView et_title;
    private TextView et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmedia_store_image);

        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);

        loadImageList();
    }

    // 加载图片列表
    @SuppressLint("Range")
    private void loadImageList() {
        String[] columns = {
                MediaStore.Images.Media._ID, // 编号
                MediaStore.Images.Media.TITLE, // 标题（文件名）
                MediaStore.Images.Media.SIZE, // 文件大小
                MediaStore.Images.Media.DATA, // 文件路径
        };
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 300 * 1024", // 300kb
                null,
                "_size DESC"
        );
        if (cursor == null) {
            return;
        }
        int count = 0;
        while (cursor.moveToNext() && count < 6) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
            imageInfo.name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
            imageInfo.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
            imageInfo.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            count++;
            mImageList.add(imageInfo);
            Log.d("x_log", "mImageList = " + mImageList);
        }
    }
}