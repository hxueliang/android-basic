package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.database.UserDBHelper;
import com.hxl.dlocaldatalasting.enity.User;
import com.hxl.dlocaldatalasting.util.ToastUtil;

import java.util.List;

public class DSQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private CheckBox ck_married;
    private UserDBHelper mHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsqlite_helper);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_query_name).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获得数据库存帮助的实例
        mHelper = UserDBHelper.getInstance(this);
        // 打开数据库的读写链接
        mHelper.openWriteLink();
        mHelper.openReadLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库链接
        mHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        Boolean married = ck_married.isChecked();
        long result = -1;

        if (v.getId() == R.id.btn_add) {
            User user = new User(
                    name,
                    Integer.parseInt(age),
                    Long.parseLong(height),
                    married
            );
            result = mHelper.insert(user);
            if (result > 0) {
                // 在 App Inspection 里可以看到添加的数据
                ToastUtil.show(this, "添加成功");
            }
        } else if (v.getId() == R.id.btn_delete) {
            result = mHelper.deleteByName(name);
            if (result >= 0) {
                // 在 App Inspection 里可以看到删除的数据
                String desc = "删除了" + result + "条数据";
                ToastUtil.show(this, desc);
            }
        } else if (v.getId() == R.id.btn_update) {
            User user = new User(
                    name,
                    Integer.parseInt(age),
                    Long.parseLong(height),
                    married
            );
            result = mHelper.update(user);
            if (result >= 0) {
                String desc = "更新了" + result + "条数据";
                ToastUtil.show(this, desc);
            }
        } else if (v.getId() == R.id.btn_query) {
            List<User> list = mHelper.queryAll();
            for (User u : list) {
                Log.d("x_log", u.toString());
            }
        } else if (v.getId() == R.id.btn_query_name) {
            List<User> list = mHelper.queryByName(name);
            for (User u : list) {
                Log.d("x_log", u.toString());
            }
        }
    }
}