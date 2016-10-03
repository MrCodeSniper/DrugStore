package com.example.android.chaoshi.bean.shop;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CWQ on 2016/9/14.
 */
public class Shop implements Parcelable {

    private String name;
    private String priceStr;
    private String url;
    private String imgUrl;
    private int imgRes;
    private double price;

    public Shop() {
    }

    public Shop(String name, String priceStr, String url, String imgUrl, int imgRes) {
        this.name = name;
        this.priceStr = priceStr;
        this.url = url;
        this.imgUrl = imgUrl;
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.priceStr);
        dest.writeString(this.url);
        dest.writeString(this.imgUrl);
        dest.writeInt(this.imgRes);
        dest.writeDouble(this.price);
    }

    protected Shop(Parcel in) {
        this.name = in.readString();
        this.priceStr = in.readString();
        this.url = in.readString();
        this.imgUrl = in.readString();
        this.imgRes = in.readInt();
        this.price = in.readDouble();
    }

    public static final Parcelable.Creator<Shop> CREATOR = new Parcelable.Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel source) {
            return new Shop(source);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
}
