package com.hxl.dlocaldatalasting;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import androidx.room.Room;

import com.hxl.dlocaldatalasting.database.BookDatabase;
import com.hxl.dlocaldatalasting.database.ShoppingDBHelper;
import com.hxl.dlocaldatalasting.enity.GoodsInfo;
import com.hxl.dlocaldatalasting.util.FileUtil;
import com.hxl.dlocaldatalasting.util.ShareUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class IMyApplication extends Application {

    private static IMyApplication mApp;
    public HashMap<String, String> infoMap = new HashMap<>();
    // 购物车中商品总数量
    public int goodsCount;
    // 4. 在自定义的Application类中声明书籍数据库的唯一实例
    private BookDatabase bookDatabase;

    // 获取类实例
    public static IMyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        // 构建书籍数据库的实例
        bookDatabase = Room.databaseBuilder(this, BookDatabase.class, "book")
                // 允许迁移数据库（发生数据库变更时，Room默认删除原数据库再创建新数据库。
                // 如此一来原来的记录会丢失，故而要改为迁移方式以便保存原有记录）
                .addMigrations()
                // 允许在主线程中操作数据库（Room默认不能在主线程中操作数据库）（因缺少必要知识，此处先允许）
                .allowMainThreadQueries()
                .build();

        // 初始化商品信息
        initGoodsInfo();
    }

    private void initGoodsInfo() {
        // 获取共享参数保存的是否首次打开参数
        boolean isFirst = ShareUtil.getInstance(this).readBoolean("first", true);
        // 获取当前App的私有下载路径
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar;

        if (isFirst) {
            // 模似网络图片下载
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info : list) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.pic);
                String path = directory + info.id + ".jpg";
                // 往存储卡保存商品图片
                FileUtil.saveImage(path, bitmap);
                // 回收位图对象
                bitmap.recycle();
                // 赋值
                info.picPath = path;
            }

            // 打开数据库，把商品信息插入到列表中
            final ShoppingDBHelper dbHelper = ShoppingDBHelper.getInstance(this);
            dbHelper.openWriteLink();
            dbHelper.insertGoodsInfos(list);
            dbHelper.closeLink();

            // 把是否首次打开写入共享参数
            ShareUtil.getInstance(this).writeBoolean("first", false);
        }
    }

    // 获取书籍数据库的实例
    public BookDatabase getBookDB() {
        return bookDatabase;
    }
}
