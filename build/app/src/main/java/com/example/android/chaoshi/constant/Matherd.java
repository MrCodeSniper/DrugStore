package com.example.android.chaoshi.constant;

import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.bean.shop.ItemTitlePager;
import com.example.android.chaoshi.util.HttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */

public class Matherd {

    /**
     * 阿伟的。。。。轮播图广告图。。。
     *
     * @return
     */
    public static List<ItemTitlePager> getTitlePagerList() {
        ArrayList<ItemTitlePager> list = new ArrayList<ItemTitlePager>();
        Document document = Jsoup.parse(HttpUtil.executeGet(Constant.url_living));
        Elements elements = document.select("div div[type=item]");
        ItemTitlePager bean = null;
        for (Element item : elements) {
            bean = new ItemTitlePager();
            bean.img = item.select("img[src]").attr("src");
            bean.href = item.select("a[href]").attr("href");
            list.add(bean);
            bean = null;
        }
        return list;
    }

    /**
     * 今日剁手价
     *
     * @return
     */
    public static List<ItemDangDang> getDangDangList1() {
        String search = "";
        try {
            search = URLEncoder.encode("水果", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getParserDangDangList(HttpUtil.executeGet(Constant.url_3c_search + search));
    }

    /**
     * 扎马路
     *
     * @return
     */
    public static List<ItemDangDang> getDangDangList2() {
        String search = "";
        try {
            search = URLEncoder.encode("洗衣粉", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getParserDangDangList(HttpUtil.executeGet(Constant.url_3c_search + search));
    }


    /**
     * 精选团购
     *
     * @return
     */
    public static List<ItemDangDang> getDangDangList3() {
        String search = "";
        try {
            search = URLEncoder.encode("笔记本电脑", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getParserDangDangList(HttpUtil.executeGet(Constant.url_3c_search + search));
    }

    /**
     * 热卖推荐
     *
     * @return
     */
    public static List<ItemDangDang> getDangDangList4() {
        String search = "";
        try {
            search = URLEncoder.encode("运动户外", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getParserDangDangList(HttpUtil.executeGet(Constant.url_3c_search + search));
    }

    /**
     * 猜你喜欢
     *
     * @return
     */
    public static List<ItemDangDang> getDangDangList5() {
        String search = "";
        try {
            search = URLEncoder.encode("成人用品", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getParserDangDangList(HttpUtil.executeGet(Constant.url_3c_search + search));
    }

    /**
     * 搜索界面
     *
     * @return
     */
    public static List<ItemDangDang> getSearchList(String shopname, String tiao_jian) {
        String search = "";
        try {
            search = URLEncoder.encode(shopname, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getParserDangDangList(HttpUtil.executeGet(Constant.url_3c_search + search + tiao_jian));
    }


    /**
     * 每次返回16条数据 返回当当网数据集合 String result = HttpUtil
     * .executeGet("http://search.dangdang.com/?key=" + URLEncoder.encode("小米",
     * "utf-8"));
     *
     * @param result
     * @return
     */
    public static List<ItemDangDang> getParserDangDangList(String result) {
        ArrayList<ItemDangDang> list = new ArrayList<ItemDangDang>();
        Document document = Jsoup.parse(result);
        Elements elements = document.select("li a.pic");
        ItemDangDang bean = null;
        for (Element item : elements) {
            bean = new ItemDangDang();
            bean.itemShopName = item.attr("title");
            bean.itemShopHref = item.attr("href");
            bean.itemShopImg = item.select("img[src]").attr("data-original");
            if (bean.itemShopImg.length() < 5) {
                bean.itemShopImg = item.select("img[src]").attr("src");
            }
            bean.itemShopProce = item.parent().select("p.price").text();
            bean.itemShopStyle = item.parent().select("p.search_hot_word").text();
            bean.itemShopType = item.parent().select("p.star").select("span[style]").attr("style");
            bean.itemShopPingLun = item.parent().select("p.star").select("a[href]").text();
            bean.itemShopMaiJiaName = item.parent().select("p.link").select("a[href]").attr("href");
            bean.itemShopMaiJiaHref = item.parent().select("p.link").select("a[href]").attr("title");
            list.add(bean);
            bean = null;
        }
        return list;
    }

}
