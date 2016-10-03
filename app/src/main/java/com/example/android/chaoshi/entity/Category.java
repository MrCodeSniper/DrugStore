package com.example.android.chaoshi.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Android on 2016/9/8.
 */
public class Category extends BmobObject {

   private String belong;
    private String tilte;
    private int cate;
    private String imageurl;
    private int gridNum;

    public int getGridNum() {
        return gridNum;
    }

    private BmobFile image;

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public void setGridNum(int gridNum) {
        this.gridNum = gridNum;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    @Override
    public String toString() {
        return "Category{" +
                "belong='" + belong + '\'' +
                ", tilte='" + tilte + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
