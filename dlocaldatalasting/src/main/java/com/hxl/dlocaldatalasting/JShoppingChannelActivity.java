package com.hxl.dlocaldatalasting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.database.ShoppingDBHelper;
import com.hxl.dlocaldatalasting.enity.GoodsInfo;
import com.hxl.dlocaldatalasting.util.ToastUtil;

import java.util.List;

public class JShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener {

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
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);

        mDBHelper = ShoppingDBHelper.getInstance(this);
        mDBHelper.openWriteLink();
        mDBHelper.openReadLink();

        showGoods();
    }

    @Override
    protected void onResume() {
        super.onResume();

        showCartInfoTotal();
    }

    /**
     * 查询购物车商品总数，并展示
     */
    private void showCartInfoTotal() {
        final int count = mDBHelper.countCartInfo();
        IMyApplication.getInstance().goodsCount = count;
        tv_count.setText(String.valueOf(count));
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

        // 移除下面的所有子视图
        gl_channel.removeAllViews();

        for (GoodsInfo info : list) {
            // 获取布局文件item_goods.xml的根视图
            final View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            final TextView tv_name = view.findViewById(R.id.tv_name);
            final ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            final TextView tv_price = view.findViewById(R.id.tv_price);
            final Button btn_add = view.findViewById(R.id.btn_add);


            tv_name.setText(info.name);
            iv_thumb.setImageURI(Uri.parse(info.picPath));
            tv_price.setText(String.valueOf((int) info.price));

            // 添加到购物车
            btn_add.setOnClickListener(v -> {
                addToCart(info.id, info.name);
            });

            // 点击商品图片，跳转到商品详情页面
            iv_thumb.setOnClickListener(v -> {
                Intent intent = new Intent(JShoppingChannelActivity.this, JShoppingDetailActivity.class);
                intent.putExtra("goods_id", info.id);
                startActivity(intent);
            });

            // 把商品视图添加到网络布局
            gl_channel.addView(view, params);
        }
    }

    /**
     * 把指定编号的商品添加到购物车
     *
     * @param goodsId
     * @param goodsName
     */
    private void addToCart(int goodsId, String goodsName) {
        // 购物车数量+1
        int count = ++IMyApplication.getInstance().goodsCount;
        tv_count.setText(String.valueOf(count));
        // 添加商品到购物车数据库
        mDBHelper.insertCartInfo(goodsId);
        ToastUtil.show(this, "已添加一部" + goodsName + "到购物车");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.iv_cart) {
            // 跳转购物车页面
            final Intent intent = new Intent(this, JShoppingCartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}