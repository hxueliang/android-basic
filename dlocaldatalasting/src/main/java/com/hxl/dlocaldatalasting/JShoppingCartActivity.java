package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.database.ShoppingDBHelper;
import com.hxl.dlocaldatalasting.enity.CartInfo;
import com.hxl.dlocaldatalasting.enity.GoodsInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JShoppingCartActivity extends AppCompatActivity {
    // 声明一个根据商品编号查找商品信息的映射，把商品信息缓存起来，这样不用每一次都去查询数据库
    private final Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_count;
    private LinearLayout ll_cart;
    private ShoppingDBHelper mDBHelper;
    // 声明一个购物车中的商品信息列表
    private List<CartInfo> mCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jshopping_cart);
        final TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车页");
        ll_cart = findViewById(R.id.ll_cart);

        tv_count = findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(IMyApplication.getInstance().goodsCount));

        mDBHelper = ShoppingDBHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCart();
    }

    // 展示购物车中的商品列表
    private void showCart() {
        // 移除所有子视图
        ll_cart.removeAllViews();
        // 查询购物车数据库中所有的商品记录
        mCartList = mDBHelper.queryAllCartInfo();
        if (mCartList.size() == 0) {
            return;
        }
        for (CartInfo info : mCartList) {
            // 根据商品编号查询商品数据库中的商品记录
            GoodsInfo goods = mDBHelper.queryGoodsInfoById(info.goodsId);
            mGoodsMap.put(goods.id, goods);

        }
    }
}