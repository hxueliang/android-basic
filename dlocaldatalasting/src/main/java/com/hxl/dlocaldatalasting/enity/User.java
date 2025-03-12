package com.hxl.dlocaldatalasting.enity;

public class User {

    public int id; // 序号
    public String name; // 姓名
    public int age; // 年龄
    public long height; // 身高
    public boolean married; // 婚否

    // 无参构造函数
    User() {

    }

    // 有参构造函数
    // 快捷键：右键 -> Generate -> Constructor
    public User(String name, int age, long height, boolean married) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.married = married;
    }

    // 方便打印
    // 快捷键：右键 -> Generate -> toString

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", married=" + married +
                '}';
    }
}
