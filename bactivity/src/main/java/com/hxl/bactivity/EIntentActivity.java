package com.hxl.bactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EIntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eintent);

        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String phoneNo = "1383838";
        if (v.getId() == R.id.btn_dial) {
            // 设置意图动作为：拨号
            intent.setAction(Intent.ACTION_DIAL);
            // 声明一个拨号Uri
            Uri uri = Uri.parse("tel:" + phoneNo);
            intent.setData(uri);

        } else if (v.getId() == R.id.btn_sms) {
            // 设置意图动作为：发短信
            intent.setAction(Intent.ACTION_SENDTO);
            // 声明一个短信Uri
            Uri uri = Uri.parse("smsto:" + phoneNo);
            intent.setData(uri);

        } else if (v.getId() == R.id.btn_my) {
            // 设置意图动作为：android.intent.action.HXL
            intent.setAction("android.intent.action.HXL");
            // 设置category
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            // 与当前手机以安装的app中，匹配其清单文件中<intent-filter>
            /**
             * <intent-filter>
             *     <action android:name="android.intent.action.MAIN" />
             *
             *     <category android:name="android.intent.category.LAUNCHER" />
             * </intent-filter>
             */

            // 并且当前手机以安装的app中，匹配其清单文件中activity中的exported为true：表示请允许其它应用打开
            /**
             * <activity android:exported="true" >

        }
        startActivity(intent);
    }
}