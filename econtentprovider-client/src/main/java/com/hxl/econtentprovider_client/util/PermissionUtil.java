package com.hxl.econtentprovider_client.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    // 检查多个权限。返回true表示已完全启用权限，返回false表示未完全使用权限
    public static boolean checkPermission(Activity act, String[] permissions, int requestCode) {
        // 安卓 6.0 之后开始彩动态权限管理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int check = PackageManager.PERMISSION_GRANTED;
            for (String permission : permissions) {
                // 1. 检查App是否开启了指定权限
                check = ContextCompat.checkSelfPermission(act, permission);
                if (check != PackageManager.PERMISSION_GRANTED) {
                    break;
                }
            }
            // 未开启该权限，则请求系统弹窗，好让用户选择是否立即开启权限
            if (check != PackageManager.PERMISSION_GRANTED) {
                // 2. 请求系统弹窗，以便用户选择是否开启权限
                ActivityCompat.requestPermissions(act, permissions, requestCode);
                return false;
            }
        }
        return true;
    }

    // 检查权限结果数组，返回true表示都已经获取授权。返回false表示至少有一个未获得授权
    public static boolean checkGrant(int[] grantResults) {
        if (grantResults != null) {
            // 遍历权限结果数组中的每条选择结果
            for (int grant : grantResults) {
                // 未获取得授权
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
