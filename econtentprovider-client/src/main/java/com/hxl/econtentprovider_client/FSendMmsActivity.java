package com.hxl.econtentprovider_client;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.util.ToastUtil;

public class FSendMmsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_add;
    private ActivityResultLauncher<Intent> mResultLauncher;
    private EditText et_phone;
    private EditText et_title;
    private EditText et_content;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsend_mms);

        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);

        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        findViewById(R.id.btn_send).setOnClickListener(this);

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
                    picUri = intent.getData();
                    if (picUri != null) {
                        // 显示刚刚选中的图片
                        iv_add.setImageURI(picUri);
                        Log.d("x_log", picUri.toString()); // content://media/picker_get_content/0/com.android.providers.media.photopicker/media/1000000038
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
        } else if (v.getId() == R.id.btn_send) {
            sendMms(
                    et_phone.getText().toString(),
                    et_title.getText().toString(),
                    et_content.getText().toString()
            );
        }
    }

    // 发送带图片的彩信
    private void sendMms(String phone, String title, String content) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Intent 的接受者将被准许读取 Intent 携带的 URI 数据
        intent.putExtra("address", phone);
        intent.putExtra("subject", title);
        intent.putExtra("sms_body", content);
        intent.putExtra(Intent.EXTRA_STREAM, picUri); // 彩信附件
        intent.setType("image/*"); // 彩信附件类型
        startActivity(intent); // 因为未指定要打开哪个页面，所以系统会在底部弹出选择窗口
        ToastUtil.show(this, "请在弹窗中选择短信或者信息应用");
    }
}