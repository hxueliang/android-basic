package com.hxl.dlocaldatalasting;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.database.ShoppingDBHelper;
import com.hxl.dlocaldatalasting.enity.GoodsInfo;

import java.util.List;

public class JShoppingChannelActivity extends AppCompatActivity {

    // 声明一个商品数据库帮助对象
    private ShoppingDBHelper mDBHelper;
    private TextView tv_count;
    private GridLayout gl_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jshopping_channel);

        final TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("手机商场");

        tv_count = findViewById(R.id.tv_count);
        gl_channel = findViewById(R.id.gl_channel);

        mDBHelper = ShoppingDBHelper.getInstance(this);
        mDBHelper.openWriteLink();
        mDBHelper.openReadLink();

        showGoods();
    }

    /**
     * 从数据库查询商品信息，并展示
     */
    private void showGoods() {
        // 商品条目是一个线性布局，设置布局的宽度为屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);

        // 查询商品数据库存中所有商品记录
        List<GoodsInfo> list = mDBHelper.queryAllGoodsInfo();
        for (GoodsInfo info : list) {
            // 获取布局文件item_goods.xml的根视图
            final View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            final TextView tv_name = view.findViewById(R.id.tv_name);
            final ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            final TextView tv_price = view.findViewById(R.id.tv_price);


            tv_name.setText(info.name);
            iv_thumb.setImageURI(Uri.parse(info.picPath));
            tv_price.setText(String.valueOf((int) info.price));

            // 把商品视图添加到网络布局
            gl_channel.addView(view, params);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.closeLink();
    }
}