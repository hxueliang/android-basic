package com.hxl.econtentprovider_client;

import android.Manifest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.entity.ImageInfo;
import com.hxl.econtentprovider_client.util.PermissionUtil;
import com.hxl.econtentprovider_client.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GMediaStoreImageActivity extends AppCompatActivity {

    // 通过string数据定义，需要的权限
    private static final String[] PERMISSIONS = new String[]{
            // Manifest.permission.READ_EXTERNAL_STORAGE, // api13以下使用
            Manifest.permission.READ_MEDIA_IMAGES // api13以上使用
    };

    private static final int PERMISSION_REQUEST_CODE = 1;

    private final List<ImageInfo> mImageList = new ArrayList<>();
    private TextView et_phone;
    private TextView et_title;
    private TextView et_content;
    private GridLayout gl_appendix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmedia_store_image);

        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);

        gl_appendix = findViewById(R.id.gl_appendix);

        // 手动让MediaStore扫描入库
        MediaScannerConnection.scanFile(
                this,
                new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()},
                null,
                null
        );

        if (PermissionUtil.checkPermission(this, PERMISSIONS, PERMISSION_REQUEST_CODE)) {
            loadImageList();

            showImageGrid();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (
                requestCode == PERMISSION_REQUEST_CODE &&
                        PermissionUtil.checkGrant(grantResults)
        ) {

            loadImageList();

            showImageGrid();

        }
    }

    // 显示图像网格
    private void showImageGrid() {
        gl_appendix.removeAllViews();

        for (ImageInfo image : mImageList) {
            // image -> ImageView
            ImageView iv_appendix = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeFile(image.path);
            iv_appendix.setImageBitmap(bitmap);
            // 设置图像视图的缩放类型
            iv_appendix.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // 设置图像视图的布局参数
            int px = Utils.dip2px(this, 110);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px, px);
            iv_appendix.setLayoutParams(params);
            // 设置图像视图的内部间距
            int padding = Utils.dip2px(this, 5);
            iv_appendix.setPadding(padding, padding, padding, padding);
            iv_appendix.setOnClickListener(v -> {

            });
            // 把图像添加到网格布局
            Log.d("x_log", iv_appendix.toString());
            gl_appendix.addView(iv_appendix);
        }
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