package com.hxl.econtentprovider_client;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.util.PermissionUtil;
import com.hxl.econtentprovider_client.util.ToastUtil;

public class CPermissionHungryActivity extends AppCompatActivity implements View.OnClickListener {

    // 通过string数据定义，需要的权限
    private static final String[] PERMISSIONS = new String[]{
            android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.WRITE_CONTACTS,
            android.Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };

    private static final int REQUEST_CODE_ALL = 1;
    private static final int REQUEST_CODE_CONTACTS = 2;
    private static final int REQUEST_CODE_SMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpermission_hungry);

        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);

        PermissionUtil.checkPermission(this, PERMISSIONS, REQUEST_CODE_ALL);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
            PermissionUtil.checkPermission(this, new String[]{PERMISSIONS[0], PERMISSIONS[1]}, REQUEST_CODE_CONTACTS);
        } else if (v.getId() == R.id.btn_sms) {
            PermissionUtil.checkPermission(this, new String[]{PERMISSIONS[2], PERMISSIONS[3]}, REQUEST_CODE_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_ALL) {
            if (PermissionUtil.checkGrant(grantResults)) {
                Log.d("x_log", "全部权限获取成功");
            } else {
                // 部分权限获取失败

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        switch (permissions[i]) {
                            case Manifest.permission.READ_CONTACTS:
                            case Manifest.permission.WRITE_CONTACTS:
                                ToastUtil.show(this, "获取通讯录读写权限失败");
                                jumpToSettings();
                                return;
                            case Manifest.permission.READ_SMS:
                            case Manifest.permission.SEND_SMS:
                                ToastUtil.show(this, "获取收发短信权限失败");
                                jumpToSettings();
                                return;
                        }
                    }
                }
            }
        } else if (requestCode == REQUEST_CODE_CONTACTS) {
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

    private void jumpToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}