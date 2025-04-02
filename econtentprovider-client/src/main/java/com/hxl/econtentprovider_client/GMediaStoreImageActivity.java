package com.hxl.econtentprovider_client;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
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
import androidx.core.content.FileProvider;

import com.hxl.econtentprovider_client.entity.ImageInfo;
import com.hxl.econtentprovider_client.util.FileUtil;
import com.hxl.econtentprovider_client.util.PermissionUtil;
import com.hxl.econtentprovider_client.util.ToastUtil;
import com.hxl.econtentprovider_client.util.Utils;

import java.io.File;
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
                sendMms(
                        et_phone.getText().toString(),
                        et_title.getText().toString(),
                        et_content.getText().toString(),
                        image.path
                );
            });
            // 把图像添加到网格布局
            Log.d("x_log", iv_appendix.toString());
            gl_appendix.addView(iv_appendix);
        }
    }

    // 发送带图片的彩信
    private void sendMms(String phone, String title, String content, String path) {
        // 根据指定路径创建一个Uri对象
        Uri uri = Uri.parse(path);
        // 兼容Android 7.0，把访问文件的Uri方式改为FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 通过FileProvider获得文件的Uri访问方式
            uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(path));
        }

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Intent 的接受者将被准许读取 Intent 携带的 URI 数据
        intent.putExtra("address", phone);
        intent.putExtra("subject", title);
        intent.putExtra("sms_body", content);
        intent.putExtra(Intent.EXTRA_STREAM, uri); // 彩信附件
        intent.setType("image/*"); // 彩信附件类型
        startActivity(intent); // 因为未指定要打开哪个页面，所以系统会在底部弹出选择窗口
        ToastUtil.show(this, "请在弹窗中选择短信或者信息应用");
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
            if (FileUtil.checkFileUri(this, imageInfo.path)) {
                count++;
                mImageList.add(imageInfo);
            }
            Log.d("x_log", "mImageList = " + mImageList);
        }
    }
}