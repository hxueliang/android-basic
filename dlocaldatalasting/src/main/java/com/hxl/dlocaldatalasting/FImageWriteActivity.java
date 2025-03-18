package com.hxl.dlocaldatalasting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.util.FileUtil;
import com.hxl.dlocaldatalasting.util.ToastUtil;

import java.io.File;

public class FImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_content;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fimage_write);

        iv_content = findViewById(R.id.iv_content);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String fileName = System.currentTimeMillis() + ".jpeg";
            // 获取当前App的私有下载目录
            path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + fileName;
            Log.d("x_log", path);
            // 从指定的资源文件中获取位图对象
            Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_girl);
            // 把位图对象保存为图片文件
            FileUtil.saveImage(path, b1);
            ToastUtil.show(this, "保存成功");
        } else if (v.getId() == R.id.btn_read) {
            // 读取图片 方法一：
            // Bitmap b2 = FileUtil.openImage(path);
            // iv_content.setImageBitmap(b2);

            // 读取图片 方法二：
            // Bitmap b2 = BitmapFactory.decodeFile(path);
            // iv_content.setImageBitmap(b2);

            // 读取图片 方法三：直接用setImageURI方法，设置图像视图的路径对象
            iv_content.setImageURI(Uri.parse(path));
        }
    }
}