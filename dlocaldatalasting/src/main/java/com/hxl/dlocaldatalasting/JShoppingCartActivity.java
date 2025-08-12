package com.hxl.dlocaldatalasting;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.database.ShoppingDBHelper;
import com.hxl.dlocaldatalasting.enity.CartInfo;
import com.hxl.dlocaldatalasting.enity.GoodsInfo;
import com.hxl.dlocaldatalasting.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {
    // 声明一个根据商品编号查找商品信息的映射，把商品信息缓存起来，这样不用每一次都去查询数据库
    private final Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_count;
    private LinearLayout ll_cart;
    private ShoppingDBHelper mDBHelper;
    // 声明一个购物车中的商品信息列表
    private List<CartInfo> mCartList;
    private TextView tv_total_price;
    private LinearLayout ll_empty;
    private View ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jshopping_cart);
        final TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车页");
        ll_cart = findViewById(R.id.ll_cart);
        tv_total_price = findViewById(R.id.tv_total_price);

        tv_count = findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(IMyApplication.getInstance().goodsCount));

        ll_empty = findViewById(R.id.ll_empty);
        ll_content = findViewById(R.id.ll_content);

        findViewById(R.id.iv_back).setOnClickListener(this);

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
            mGoodsMap.put(info.goodsId, goods);

            // 获取布局文件item_cart.xml的根视图
            final View view = LayoutInflater.from(this).inflate(R.layout.item_cart, null);
            final ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            final TextView tv_name = view.findViewById(R.id.tv_name);
            final TextView tv_desc = view.findViewById(R.id.tv_desc);
            final TextView tv_count = view.findViewById(R.id.tv_count);
            final TextView tv_price = view.findViewById(R.id.tv_price);
            final TextView tv_sum = view.findViewById(R.id.tv_sum);

            iv_thumb.setImageURI(Uri.parse(goods.picPath));
            tv_name.setText(goods.name);
            tv_desc.setText(goods.description);
            tv_count.setText(String.valueOf(info.count));
            tv_price.setText(String.valueOf((int) goods.price));
            tv_sum.setText(String.valueOf((int) (info.count * goods.price)));

            // 给商品行添加长按事件。长按商品行就删除该商品
            view.setOnLongClickListener(v -> {
                final AlertDialog.Builder builder = new AlertDialog.Builder(JShoppingCartActivity.this);
                builder.setMessage("是否从购物车中删除" + goods.name + "?");
                builder.setPositiveButton("是", (dialog, which) -> {
                    // 移除当前视图
                    ll_cart.removeView(v);
                    // 删除当前商品
                    deleteGoods(info);
                });
                builder.setNegativeButton("否", null);
                builder.create().show();
                return true;
            });

            // 往购物车列表添加该商品行
            ll_cart.addView(view);
        }

        refreshTotalPrice();
    }

    private void deleteGoods(CartInfo info) {
        IMyApplication.getInstance().goodsCount -= info.count;
        // 从购物车数据库中删除商品
        mDBHelper.deleteCartInfoByGoodsId(info.goodsId);

        // 从购物车的列表中删除商品
        CartInfo removed = null;
        for (CartInfo cartInfo : mCartList) {
            if (cartInfo.goodsId == info.goodsId) {
                removed = cartInfo;
                break;
            }
        }
        mCartList.remove(removed);

        // 显示最新商品数量
        showCount();

        // 删除mGoodsMap中的缓存数据
        ToastUtil.show(this, "已从购物车中删除" + mGoodsMap.get(info.goodsId).name);
        mGoodsMap.remove(info.goodsId);

        // 刷新购物车中所有商品的总金额
        refreshTotalPrice();
    }

    // 显示购物车图标中的商品数量
    private void showCount() {
        int goodsCount = IMyApplication.getInstance().goodsCount;
        tv_count.setText(String.valueOf(goodsCount));
        if (goodsCount == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
            ll_cart.removeAllViews();
        } else {
            ll_empty.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
        }
    }

    // 重新计算购物车中的商品总金额
    private void refreshTotalPrice() {
        int totalPrice = 0;
        for (CartInfo info : mCartList) {
            final GoodsInfo goods = mGoodsMap.get(info.goodsId);
            totalPrice += goods.price * info.count;
        }
        tv_total_price.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }
}