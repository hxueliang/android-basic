package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.util.FileUtil;
import com.hxl.dlocaldatalasting.util.ToastUtil;

import java.io.File;

public class EFileWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private CheckBox ck_married;
    private TextView tv_show_data;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efile_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);
        tv_show_data = findViewById(R.id.tv_show_data);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        Boolean married = ck_married.isChecked();

        if (v.getId() == R.id.btn_save) {
            String sb = "姓名:" + name +
                    "\n年龄:" + age +
                    "\n身高:" + height +
                    "\n婚否:" + (married ? "是" : "否");

            String fileName = System.currentTimeMillis() + ".txt";
            String directory = null;

            // 外部存储的私有空间
            // /storage/emulated/0/Android/data/com.hxl.dlocaldatalasting/files/Download/
            // directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();

            // 外部存储的公共空间
            /**
             * 1. 代码修改 directory
             * 2. 在清单文件配置权限
             * 3. run app
             * 4. 手机回到桌面，长按app图标 -> App info -> Permissions -> Files and media -> Allow access to media only
             */
            // /storage/emulated/0/Download/
            // directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

            /**
             * 测试 外部存储 "私有空间"和"公共空间"的区别
             * 1.卸载app
             * 2./storage/emulated/0/Android/data/ 里的包名文件已经不在了
             * 3./storage/emulated/0/Download 里的文件还在
             */

            // 内部存储空间
            // /data/data/com.hxl.dlocaldatalasting/files/
            directory = getFilesDir().toString();

            path = directory + File.separatorChar + fileName;
            Log.d("x_log", path);
            FileUtil.saveText(path, sb);
            ToastUtil.show(this, "保存成功");
        } else if (v.getId() == R.id.btn_read) {
            tv_show_data.setText(FileUtil.openText(path));
        }
    }
}