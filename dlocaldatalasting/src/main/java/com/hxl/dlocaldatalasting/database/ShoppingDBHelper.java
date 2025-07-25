package com.hxl.dlocaldatalasting.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hxl.dlocaldatalasting.enity.CartInfo;
import com.hxl.dlocaldatalasting.enity.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "shopping.db";
    // 商品信息表
    private static final String TABLE_GOODS_INFO = "goods_info";
    // 购物车信息表
    private static final String TABLE_CART_INFO = "cart_info";
    private static final int DB_VERSION = 1;
    private static ShoppingDBHelper mHelper = null;
    private SQLiteDatabase mWDB = null;
    private SQLiteDatabase mRDB = null;

    private ShoppingDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 利用单例模式获取数据库帮助器的唯一实例
    public static ShoppingDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new ShoppingDBHelper(context);
        }
        return mHelper;
    }

    // 打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    // 打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    // 关闭数据库连接
    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }
        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }

    // 创建数据库，执行SQL语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建商品信息表
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_INFO +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " description VARCHAR NOT NULL," +
                " price FLOAT NOT NULL," +
                " pic_path VARCHAR NOT NULL);";
        db.execSQL(sql);

        // 创建购物车信息表

        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_CART_INFO +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " goods_id INTEGER NOT NULL," +
                " count INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 添加多条商品信息
    public void insertGoodsInfos(List<GoodsInfo> list) {
        // 插入多条记录，要么全部成功，要么全部失败
        try {
            mWDB.beginTransaction();

            for (GoodsInfo info : list) {
                ContentValues values = new ContentValues();
                values.put("name", info.name);
                values.put("description", info.description);
                values.put("price", info.price);
                values.put("pic_path", info.picPath);
                mWDB.insert(TABLE_GOODS_INFO, null, values);
            }

            mWDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mWDB.endTransaction();
        }
    }

    public List<GoodsInfo> queryAllGoodsInfo() {
        String sql = "select * from " + TABLE_GOODS_INFO;
        List<GoodsInfo> list = new ArrayList<>();
        Cursor cursor = mRDB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            GoodsInfo info = new GoodsInfo();
            info.id = cursor.getInt(0);
            info.name = cursor.getString(1);
            info.description = cursor.getString(2);
            info.price = cursor.getFloat(3);
            info.picPath = cursor.getString(4);
            list.add(info);
        }
        cursor.close();
        return list;
    }

    /**
     * 添加商品到购物车数据库
     *
     * @param goodsId
     */
    public void insertCartInfo(int goodsId) {
        CartInfo cartInfo = queryCartInfoByGoodsInfo(goodsId);
        ContentValues values = new ContentValues();
        values.put("goods_id", goodsId);
        if (cartInfo == null) {
            // 如果购物车不存在商品，添加一条信息
            values.put("count", 1);
            mWDB.insert(TABLE_CART_INFO, null, values);
        } else {
            // 如果购物车存在商品，更新商品数量
            values.put("_id", cartInfo.id);
            values.put("count", ++cartInfo.count);
            mWDB.update(TABLE_CART_INFO, values, "_id=?", new String[]{String.valueOf(cartInfo.id)});
        }

    }

    private CartInfo queryCartInfoByGoodsInfo(int goodsId) {
        final Cursor cursor = mRDB.query(TABLE_CART_INFO, null, "goods_id=?", new String[]{String.valueOf(goodsId)}, null, null, null, null);
        CartInfo info = null;
        if (cursor.moveToNext()) {
            info = new CartInfo();
            info.id = cursor.getInt(0);
            info.goodsId = cursor.getInt(1);
            info.count = cursor.getInt(2);
        }
        return info;
    }

    /**
     * 统计购物车商品的总数量
     */
    public int countCartInfo() {
        int count = 0;
        String sql = "select sum(count) from " + TABLE_CART_INFO;
        final Cursor cursor = mRDB.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }

        return count;
    }

    public List<CartInfo> queryAllCartInfo() {
        List<CartInfo> list = new ArrayList<>();
        final Cursor cursor = mRDB.query(TABLE_CART_INFO, null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            CartInfo info = new CartInfo();
            info.id = cursor.getInt(0);
            info.goodsId = cursor.getInt(1);
            info.count = cursor.getInt(2);
            list.add(info);
        }
        return list;
    }

    // 根据商品ID查询商品信息
    public GoodsInfo queryGoodsInfoById(int goodsId) {
        GoodsInfo info = null;
        final Cursor cursor = mRDB.query(TABLE_GOODS_INFO, null, "id=?", new String[]{String.valueOf(goodsId)}, null, null, null);
        if (cursor.moveToNext()) {
            info = new GoodsInfo();
            info.id = cursor.getInt(0);
            info.name = cursor.getString(1);
            info.description = cursor.getString(2);
            info.price = cursor.getFloat(3);
            info.picPath = cursor.getString(4);
        }
        return info;
    }
}
