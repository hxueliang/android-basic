package com.hxl.bactivity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IMetaDataActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_meta_data;
    private PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imeta_data);

        tv_meta_data = findViewById(R.id.tv_meta_data);
        findViewById(R.id.btn_get_meta).setOnClickListener(this);

        // 获取应用包管理器
        pm = getPackageManager();
    }

    @Override
    public void onClick(View v) {
        try {
            // 从应用包管理器中获取当前的活动信息
            ActivityInfo info = pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            // 获取活动附加元数据信息
            Bundle bundle = info.metaData;
            String myName = bundle.getString("myName");
            tv_meta_data.setText(myName);

        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}