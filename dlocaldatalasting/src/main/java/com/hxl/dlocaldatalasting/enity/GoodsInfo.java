package com.hxl.dlocaldatalasting.enity;

import androidx.room.Entity;

import com.hxl.dlocaldatalasting.R;

import java.util.ArrayList;

// 1. 编写书籍信息表对应的实体类，该类添加"@Entity"注解
@Entity
public class GoodsInfo {

    // 声明一个手机商品名称数组
    private static final String[] mNameArray = {
            "iPhone11", "Mate30", "小米10", "OPPO Reno3", "vivo x30", "荣耀30s"
    };
    
    // 声明一个手机商品的描述数组
    private static final String[] mDescArray = {
            "Apple iPhone11 256GB 绿色 4G全网通手机",
            "华为 HUAWEI Mate30 8GB+256GB 丹霞橙 5G全网通 全面屏手机",
            "小米 MI10 8GB+256GB 钛银黑 5G手机 游戏拍照手机",
            "OPPO Reno3 8GB+128GB 蓝色星夜 双模5G 拍照游戏智能手机",
            "vivo X30 8GB+128GB 绯支 5G全网通 美颜拍照手机",
            "荣耀30s 8GB+128GB 蝶羽红 5G芯片 自拍全面屏手机"
    };

    // 声明一个手机商品的价格数组
    private static final float[] mPriceArray = {
            6299, 4999, 3999, 2999, 2998, 2399
    };

    // 声明一个手机商品的大图数组
    private static final int[] mPicArray = {
            R.drawable.grey,
            R.drawable.red,
            R.drawable.yellow,
            R.drawable.green,
            R.drawable.orange,
            R.drawable.pink
    };

    public int id;
    public String name; // 书名
    public String description; // 描述
    public float price; // 价格
    public String picPath; // 大图的保存路径
    public int pic; // 大图的资源编号(本地图片编号，用来模拟网络请求)

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.id = i;
            info.name = mNameArray[i];
            info.description = mDescArray[i];
            info.price = mPriceArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
