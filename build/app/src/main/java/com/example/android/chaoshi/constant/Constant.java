package com.example.android.chaoshi.constant;

import android.graphics.Bitmap;

import com.example.android.chaoshi.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by Android on 2016/9/6.
 */
public class Constant {

    public static final String SEARCH_URL = "http://search.dangdang.com";
    public static final String[] CATEGORY = new String[]{"食品生鲜", "进口食品", "酒水饮料", "厨卫清洁", "母婴玩具", "美容护理"};
    public static final int[] SELECTOR = {R.drawable.shipin_selector, R.drawable.jinkou_selector, R.drawable.jiushui_selector, R.drawable.chuwei_selector, R.drawable.muyin_selector, R.drawable.meirong_selector};
    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.home_category_3_icon_default)
            .showImageOnFail(R.drawable.home_category_3_icon_default)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public static final String url_bokk = "http://book.dangdang.com/";
    public static final String url_living = "http://living.dangdang.com/";
    public static final String url_3c = "http://3c.dangdang.com/";


    public static final String url_3c_search = "http://search.dangdang.com/?key=";
    public static final String url_3c_pager = "&page_index=";

    /**
     * 综合排序
     */
    public static final String sort_default = "&sort_type=sort_default";
    /**
     * 销量排序
     */
    public static final String sort_sale_amt_desc = "&sort_type=sort_sale_amt_desc";
    /**
     * 好评排序
     */
    public static final String sort_score_desc = "&sort_type=sort_score_desc";
    /**
     * 最新排序
     */
    public static final String sort_last_changed_date_desc = "&sort_type=sort_last_changed_date_desc";
    /**
     * 价格排序
     */
    public static final String sort_xlowprice_desc = "&sort_type=sort_default";
    /**
     * 价格 － － － 范围
     */
    public static final String lowpANDhighp = "&lowp=500&highp=1000";


}
