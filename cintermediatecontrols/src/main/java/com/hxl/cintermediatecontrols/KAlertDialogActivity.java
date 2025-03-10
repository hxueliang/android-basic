package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class KAlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView vt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalert_dialog);

        vt_message = findViewById(R.id.tv_message);
        findViewById(R.id.btn_alert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 创建提醒对话框的建造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置标题
        builder.setTitle("尊敬的用户");
        // 设置内容
        builder.setMessage("你真的要卸载吗？");
        // 设置肯定按钮文本和其点击监听器
        builder.setPositiveButton("残忍卸载", (dialog, which) -> {
            vt_message.setText("卸载成功");
        });
        // 设置否定按钮文本和其点击监听器
        builder.setNegativeButton("我现想想", (dialog, which) -> {
            vt_message.setText("取消卸载");
        });
        // 根据建造器构建提醒对话框对象
        AlertDialog dialog = builder.create(); // .var + 回车，快捷键: option + 回车
        // 显示对话框
        dialog.show();
    }
}