package com.example.android.chaoshi.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 2016/9/7.
 */
public class ShopItem implements Parcelable {

    /** 商品图片链接 */
    public  String imgurl;
    /** 商品价格*/
    public String number;
    /** 商品标题*/
    public String title;
    /** 商品评论条目数量*/
    public String pinglun;
    /** 商品详情的链接*/
    public String href ;
    /** 商品的好评率*/
    public String haoping ;
    /** 商品当前活动的类型图片链接，比如冰点价神马的*/
    public String type ;



    public ShopItem()
    {

    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "imgurl='" + imgurl + '\'' +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", pinglun='" + pinglun + '\'' +
                ", href='" + href + '\'' +
                ", haoping='" + haoping + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeString(imgurl);
        out.writeString(number);
        out.writeString(title);
        out.writeString(pinglun);
        out.writeString(href);
        out.writeString(haoping);
        out.writeString(type);

    }

    public static final Parcelable.Creator<ShopItem> CREATOR = new Creator<ShopItem>()
    {
        @Override
        public ShopItem[] newArray(int size)
        {
            return new ShopItem[size];
        }

        @Override
        public ShopItem createFromParcel(Parcel in)
        {
            return new ShopItem(in);
        }
    };

    public ShopItem(Parcel in)
    {
       imgurl = in.readString();
        number= in.readString();
     title = in.readString();
      pinglun  = in.readString();
        href= in.readString();
        haoping= in.readString();
       type = in.readString();
    }
}
