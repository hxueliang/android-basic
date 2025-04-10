package com.hxl.econtentprovider_client;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.util.PermissionUtil;
import com.hxl.econtentprovider_client.util.ToastUtil;

public class BPermissionLazyActivity extends AppCompatActivity implements View.OnClickListener {

    // 通过string数据定义，需要通讯录的读写权限
    private static final String[] PERMISSIONS_CONTACTS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };

    // 短信读写权限
    private static final String[] PERMISSIONS_SMS = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };

    private static final int REQUEST_CODE_CONTACTS = 1;
    private static final int REQUEST_CODE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpermission_lazy);

        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
            /**
             * 动态申请权限的步骤
             * 1. 检查App是否开启了指定权限
             *      调用 ContextCompat 的 checkSelfPermission 方法
             * 2. 请求系统弹窗，以便用户选择是否开启权限
             *      调用 ActivityCompat 的 requestPermissions 方法，即可命令系统自动弹出权限申请窗口
             * 3. 判断用户的权限选择结果
             *      重写活动页面的权限请求回调方法 onRequestPermissionsResult，在该方法内部处理用户权限选择结果
             */
            PermissionUtil.checkPermission(this, PERMISSIONS_CONTACTS, REQUEST_CODE_CONTACTS);
        } else if (v.getId() == R.id.btn_sms) {
            PermissionUtil.checkPermission(this, PERMISSIONS_SMS, REQUEST_CODE_SMS);
        }
    }

    // 3. 重写活动页面的权限请求回调方法 onRequestPermissionsResult，在该方法内部处理用户权限选择结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (PermissionUtil.checkGrant(grantResults)) {
                Log.d("x_log", "通讯录权限获取成功");
            } else {
                ToastUtil.show(this, "获取通讯录读写权限失败");
                jumpToSettings();
            }
        } else if (requestCode == REQUEST_CODE_SMS) {
            if (PermissionUtil.checkGrant(grantResults)) {
                Log.d("x_log", "收发短信权限获取成功");
            } else {
                ToastUtil.show(this, "获取收发短信权限失败");
                jumpToSettings();
            }
        }
    }

    // 跳转到应用设置界面
    private void jumpToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}