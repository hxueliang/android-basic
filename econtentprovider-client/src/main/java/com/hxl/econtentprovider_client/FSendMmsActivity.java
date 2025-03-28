package com.hxl.econtentprovider_client;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class FSendMmsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_add;
    private ActivityResultLauncher<Intent> mResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsend_mms);

        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);

        // 跳转到系统相册，选择图片，并返回
        mResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                /**
                 * Todo: 模似器相册中，没有图片
                 * 1. Device Explorer -> /storage/emulated/0/Download -> 右键 -> 点击Upload -> 导入图片
                 * 2. 在模似器的app中，点+号 -> 在弹出选图面板中 -> 点击三个点 -> Browse -> 侧边栏 -> Downloads
                 */
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    // 选择图片的路径
                    final Uri picUri = intent.getData();
                    if (picUri != null) {
                        // 显示刚刚选中的图片
                        iv_add.setImageURI(picUri);
                        Log.d("x_log", "picUrl:" + picUri);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_add) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            mResultLauncher.launch(intent);
        }
    }
}