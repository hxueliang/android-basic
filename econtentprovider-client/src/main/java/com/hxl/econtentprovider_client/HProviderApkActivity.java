package com.hxl.econtentprovider_client;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.hxl.econtentprovider_client.util.PermissionUtil;
import com.hxl.econtentprovider_client.util.ToastUtil;

import java.io.File;

public class HProviderApkActivity extends AppCompatActivity implements View.OnClickListener {
    // 通过string数据定义，需要的权限
    private static final String[] PERMISSIONS = new String[]{
            // Manifest.permission.READ_EXTERNAL_STORAGE, // api13以下使用
            Manifest.permission.READ_MEDIA_IMAGES // api13以上使用
    };

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hprovider_apk);

        findViewById(R.id.btn_install).setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && PermissionUtil.checkGrant(grantResults)) {
            installApk();
        }
    }

    @Override
    public void onClick(View v) {
        installApk();
        // Android 11 之后获取 MANAGE_EXTERNAL_STORAGE 权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d("x_log", "Android 11+");
            checkAndInstall();
        } else {
            if (PermissionUtil.checkPermission(this, PERMISSIONS, PERMISSION_REQUEST_CODE)) {
                installApk();
            }
        }
    }

    private void checkAndInstall() {
        // 检查是否拥有 MANAGE_EXTERNAL_STORAGE 权限，没有则跳转到设置页面
        if (!Environment.isExternalStorageManager()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent);
        } else {
            installApk();
        }
    }

    private void installApk() {
        String apkPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/cintermediatecontrols-debug.apk";
        Log.d("x_log", apkPath);
        // 获取应用包管理器
        PackageManager pm = getPackageManager();
        // 获取apk文件的包信息
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (pi == null) {
            ToastUtil.show(this, "安装文件已经损坏");
        }

        // 根据指定路径创建一个Uri对象
        Uri uri = Uri.parse(apkPath);
        // 兼容Android 7.0，把访问文件的Uri方式改为FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 通过FileProvider获得文件的Uri访问方式
            uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(apkPath));
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Intent 的接受者将被准许读取 Intent 携带的 URI 数据
        intent.setDataAndType(uri, "application/vnd.android.package-archive"); // 设置Uri数据类型为APK文件
        startActivity(intent); // 启动系统自带应用安装程序
    }
}