package com.example.android.chaoshi.bean.shop;

/**
 * Created by CWQ on 2016/9/7.
 */
public class Banner {

    private String imgUrl;
    private String url;

    public Banner() {
    }

    public Banner(String imgUrl, String url) {
        this.imgUrl = imgUrl;
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
