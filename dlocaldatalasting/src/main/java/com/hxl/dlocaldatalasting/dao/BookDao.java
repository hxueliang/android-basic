package com.hxl.dlocaldatalasting.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hxl.dlocaldatalasting.enity.BookInfo;

import java.util.List;

/**
 * DAO(Data Access Object)是一个数据访问接口，数据访问：顾名思义就是与数据库打交道。夹在业务逻辑与数据库资源中间。
 * <p>
 * 在核心J2EE模式中是这样介绍DAO模式的：为了建立一个健壮的J2EE应用，应该将所有对数据源的访问操作抽象封装在一个公共API中。
 * 用程序设计的语言来说，就是建立一个接口，接口中定义了此应用程序中将会用到的所有事务方法。
 * 在这个应用程序中，当需要和数据源进行交互的时候则使用这个接口，并且编写一个单独的类来实现这个接口在逻辑上对应这个特定的数据存储。
 */

// 2. 编写书籍信息表对应的持久化类，该类添加"@Dao"注解

/**
 * BookDao 接口
 * <p>
 * 并没有具体的实现，会自动生成BookDaoImpl类
 */
@Dao
public interface BookDao {

    @Insert
    void insert(BookInfo... book);

    @Delete
    void delete(BookInfo... book);

    // 删除所有书籍信息
    @Query("DELETE FROM BookInfo")
    void deleteAll();

    @Update
    int update(BookInfo... book);

    // 查询所有书籍（BookInfo: 不区分大小写，建设写成和类名一致）
    @Query("SELECT * FROM BookInfo")
    List<BookInfo> queryAll();

    // 根据名字查询书籍
    @Query("SELECT * FROM BookInfo WHERE name = :name ORDER BY id DESC limit 1")
    BookInfo queryByName(String name);
}
