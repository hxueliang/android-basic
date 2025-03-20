package com.hxl.dlocaldatalasting.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hxl.dlocaldatalasting.enity.BookInfo;

import java.util.List;

// 2. 编写书籍信息表对应的持久化类，该类添加"@Dao"注解
@Dao
public interface BookDao {

    @Insert
    void insert(BookInfo... book);

    @Delete
    void delete(BookInfo... book);

    // 删除所有书籍信息
    @Query("DELETE FROM BookInfo")
    void deleteAll(BookInfo... book);

    @Update
    int update(BookInfo... book);

    // 查询所有书籍
    @Query("SELECT * FROM BookInfo")
    List<BookInfo> queryAll();

    // 根据名字查询书籍
    @Query("SELECT * FROM BookInfo WHERE name = :name ORDER BY id DESC limit 1")
    BookInfo queryByName(String name);
}
