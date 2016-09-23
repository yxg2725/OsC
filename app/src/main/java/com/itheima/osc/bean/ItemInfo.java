package com.itheima.osc.bean;

import android.widget.ImageView;

/**
 * Created by admin on 2016/9/17.
 */
public class ItemInfo {
    public String content;
    public String name;
    public String desc;
    public String phone;
    public int img;
    public String time;
    public String comment;

    public ItemInfo(int img, String time,String content, String name, String desc, String phone,  String comment) {
        this.content = content;
        this.name = name;
        this.desc = desc;
        this.phone = phone;
        this.img = img;
        this.time = time;
        this.comment = comment;
    }


    public ItemInfo(String content, String name, String phone, String time) {
        this.content = content;
        this.name = name;
        this.phone = phone;
        this.time = time;
    }
}
