package com.example.android.chaoshi.model.Imp;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android.chaoshi.bean.shop.Item3CShuMa;
import com.example.android.chaoshi.bean.shop.Item3CTuBiao;
import com.example.android.chaoshi.bean.shop.ItemTitlePager;
import com.example.android.chaoshi.util.HttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Android on 2016/9/13.
 */
public class DataModel {
    private Activity context;

    public DataModel(Activity context) {
        this.context = context;
    }
    /**
     *拿到网页返回的字符串集，当拿到数据时回调
     */
    public void getStringData(final ResultCallBack callback){
        final String urlString = "http://3c.dangdang.com";

        AsyncTask<String,Void,String> task = new AsyncTask<String,Void,String>(){

            @Override
            protected String doInBackground(String... params) {
                String result = HttpUtil.executeGet(urlString );
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                callback.onLoadData(result);
            }
        };
        task.execute();


    }

    /**
     * * type=0,返回限时抢, 集合,10条,大图 0-10
     * type=1,返回手机/配件, 集合,7条,大图 10-17
     * type=2,返回热销手机 , 集合,10条,小图 17-27
     * type=3,返回热销配件 , 集合,10条,小图 27-37
     * type=4,返回电脑办公 , 集合,8条,大图 37-45
      * type=5,返回电脑整机 , 集合,10条,小图 45-55
     * type=6,返回存储网络 , 集合,10条,小图 55-65
     * type=7,返回外设办公 , 集合,10条,小图 65-75
     * type=8,返回数码影音 , 集合,8条,大图 75-83
     * type=9,返回苹果/配件 , 集合,10条,小图 83-93
     * type=10,返回摄影摄相 , 集合,4条,小图 93-97
     * type=11,返回视听影音 , 集合,10条,小图 97-107
     * type=12,返回小家电 , 集合,24条,大图 107-131
     * type=13,返回厨电 , 集合,10条,小图 131-141
     * type=14,返回生活 , 集合,10条,小图 141-151
     * type=15,返回个护 , 集合,10条,小图 151-161
     * type=16,返回大家电 , 集合,24条,大图 161-185
     * type=17,返回冰箱 , 集合,10条,小图 185-195
     * http://3c.dangdang.com/
     *解析出所需要的实体集合
     * @param result  网站拿到的字符串集合
     * @param type    要拿到的数据集合类型
     * @return
     */
    public List<Item3CShuMa> getParser3CShuMaList(String result, int type) {
        ArrayList<Item3CShuMa> list = new ArrayList<Item3CShuMa>();
        Document document = Jsoup.parse(result);
        Elements elements = document.select("ul li a[class=img]");
        Item3CShuMa bean = null;
        ;
        for (org.jsoup.nodes.Element item : elements) {
            bean = new Item3CShuMa();
            bean.img = item.select("img[src]").attr("src");
            bean.href = item.select("a[href]").attr("href");
            bean.title = item.select("img[src]").attr("alt");
            bean.num = item.parent().select("span.rob").text();
            list.add(bean);
            bean = null;
        }
        try {
            switch (type) {
                case 0:
                    return list.subList(0, 10);
                case 1:
                    return list.subList(10, 17);
                case 2:
                    return list.subList(17, 27);
                case 3:
                    return list.subList(27, 37);
                case 4:
                    return list.subList(37, 45);
                case 5:
                    return list.subList(45, 55);
                case 6:
                    return list.subList(55, 65);
                case 7:
                    return list.subList(65, 75);
                case 8:
                    return list.subList(75, 83);
                case 9:
                    return list.subList(83, 93);
                case 10:
                    return list.subList(93, 97);
                case 11:
                    return list.subList(97, 107);
                case 12:
                    return list.subList(107, 131);
                case 13:
                    return list.subList(131, 141);
                case 14:
                    return list.subList(141, 151);
                case 15:
                    return list.subList(151, 161);
                case 16:
                    return list.subList(161, 185);
                case 17:
                    return list.subList(185, 195);

                default:
                    return list;
            }
        } catch (Exception e) {

        }
        Log.d("hua", list.toString());

        return list;
    }

    /**
     //	 * 3c界面轮播图三张
     //	 *
     //	 * @param result
     //	 * @param type
     //	 * @return
     //	 */
	public List<ItemTitlePager> getParser3CTitlePagerList(String result, int type) {
		ArrayList<ItemTitlePager> list = new ArrayList<ItemTitlePager>();
		Document document = Jsoup.parse(result);
		Elements elements = document.select("div div[type=item]");
		ItemTitlePager bean = null;
		for (org.jsoup.nodes.Element item : elements) {
			bean = new ItemTitlePager();
			bean.img = item.select("img[src]").attr("src");
			bean.href = item.select("a[href]").attr("href");
			list.add(bean);
			bean = null;
		}
		return list;
	}

    /**
     //	 * 3c界面各种品牌的小图标 点击跳转卖家详情界面
     //	 *
     //	 * @param result
     //	 * @param type
     //	 * @return
     //	 */
	public  List<Item3CTuBiao> getParser3CTuBiaoList(String result, int type) {
		ArrayList<Item3CTuBiao> list = new ArrayList<Item3CTuBiao>();
		Document document = Jsoup.parse(result);
	 Elements elements = document.select("ul li a[class=pic]");
		Log.e("", "" + elements.size());
		Item3CTuBiao bean = null;
		for (org.jsoup.nodes.Element item : elements) {
			bean = new Item3CTuBiao();
			bean.src = item.select("img[src]").attr("src");
			bean.href = item.select("a[href]").attr("href");
		list.add(bean);
			bean = null;
		}
		return list;
	}

}
