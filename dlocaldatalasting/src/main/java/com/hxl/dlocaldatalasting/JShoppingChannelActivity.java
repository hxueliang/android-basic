package com.hxl.dlocaldatalasting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.database.ShoppingDBHelper;

public class JShoppingChannelActivity extends AppCompatActivity {

    // 声明一个商品数据库帮助对象
    private ShoppingDBHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jshopping_channel);

        mDBHelper = ShoppingDBHelper.getInstance(this);
        mDBHelper.openWriteLink();
        mDBHelper.openReadLink();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.closeLink();
    }
}