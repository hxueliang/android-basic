package com.hxl.fadvancedcontrols.bean;

import com.hxl.fadvancedcontrols.R;

import java.util.ArrayList;
import java.util.List;

public class DPlanet {
    private static final int[] iconArray = {
            R.drawable.orange,
            R.drawable.green,
            R.drawable.blue,
            R.drawable.ic_boy,
            R.drawable.ic_girl,
            R.drawable.yellow,
    };
    private static final String[] nameArray = {"金星", "木星", "水星", "地球", "火星", "土星"};
    private static final String[] descArray = {
            "金星金星金星金星金星金星金星金星金星金星金星",
            "木星木星木星木星木星木星木星木星木星木星木星木星",
            "水星水星水星水星水星水星水星水星水星",
            "地球地球地球地球地球地球地球地球地球地球地球",
            "火星火星火星火星火星火星火星火星火星火星",
            "土星土星土星土星土星土星土星土星土星土星土星土星土星"
    };

    public int image; // 图标
    public String name; // 名称
    public String desc; // 描述

    public DPlanet(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    public static List<DPlanet> getDefaultList() {
        List<DPlanet> planetList = new ArrayList<>();
        for (int i = 0; i < iconArray.length; i++) {
            planetList.add(new DPlanet(iconArray[i], nameArray[i], descArray[i]));
        }
        return planetList;
    }

}
