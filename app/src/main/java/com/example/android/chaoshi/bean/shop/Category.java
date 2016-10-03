package com.example.android.chaoshi.bean.shop;

/**
 * Created by CWQ on 2016/9/8.
 */
public class Category {

    private int icon;
    private String title;
    private String url;

    public Category() {
    }

    public Category(int icon, String title, String url) {
        this.icon = icon;
        this.title = title;
        this.url = url;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
