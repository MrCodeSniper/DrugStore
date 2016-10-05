package com.example.android.chaoshi.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.android.chaoshi.base.CrashHandler;
import com.example.android.chaoshi.bean.shop.Commodity;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.bean.shop.Store;
import com.example.android.chaoshi.bean.user.User_New;
import com.example.android.chaoshi.util.UserLocalData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ProjectApp extends MyApplication {


    private static ProjectApp mContext;
    private static List<FragmentActivity> mActivitys;
    private static Toast mToast;
    private static Handler mHandler;

    private static HashMap<Integer, List<Commodity>> mCarts;
    private static ArrayList<Store> mStores;




    public static final int STATE_LOGOUT = 0;
    public static final int STATE_LOGIN = 1;
    public static Context context;
    public static int curState = STATE_LOGOUT;
    public static String username;
    public static String pwd;
    private Context applicationcontext;


    private User_New user;


    private void initData() {
        mCarts = new HashMap<>();
        mStores = new ArrayList<>();
    }

    /**
     * 当前是否是debug模式
     */
    public static boolean isRelease = true;


    public static HashMap<Integer, List<Commodity>> getCarts() {
        return mCarts;
    }

    public static ArrayList<Store> getStores() {
        return mStores;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mActivitys = new ArrayList<FragmentActivity>();
        mHandler = new Handler();
        initData();
        x.Ext.init(this);
        // 回调：通过框架调我们写的实现类的对象
        // 告诉框架调那个对象
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new CrashHandler(this));

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);

        Bmob.initialize(this, "3a4be38c7456063eb274888149dd7c60");
    }


    public static ProjectApp getInstance() {
        return mContext;
    }

    public static List<FragmentActivity> getmActivitys() {
        return mActivitys;
    }

    public void finish() {
        // 把所有的activity finish
        for (Activity activity : mActivitys) {
            activity.finish();
        }
        // 结束进程
//        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static Toast getToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
            return mToast;
        }
        mToast.setText(content);
        return mToast;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static void addShopEntity(ItemDangDang itemDangDang) {
        mCarts.clear();
        mStores.clear();
        Double price = 0.0;
        try {
            if (itemDangDang.itemShopProce != null) {
                String substring = itemDangDang.itemShopProce.substring(1, itemDangDang.itemShopProce.length());
                String regex = "(\\d)+";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(substring);
                if (matcher.find()) {
                    String group = matcher.group();
                    price = Double.parseDouble(group);
                } else {
                    price = new Double(10);
                }
            } else {
                price = new Double(10);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Store store = new Store(itemDangDang.itemShopMaiJiaName);
        Commodity item = new Commodity(itemDangDang.itemShopName, itemDangDang.itemShopHref, 1, price);
        mStores.add(store);
        mStores.add(store);
        ArrayList<Commodity> mCommoditys = new ArrayList<Commodity>();
        mCommoditys.add(item);
        mCarts.put(1, mCommoditys);
    }


    private void initUser(){

        this.user = UserLocalData.getUser(this);
    }


    public User_New getUser(){

        return user;
    }


    public void putUser(User_New user,String token){
        this.user = user;
        UserLocalData.putUser(this,user);
        UserLocalData.putToken(this,token);
    }

    public void clearUser(){
        this.user =null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);


    }


    public String getToken(){

        return  UserLocalData.getToken(this);
    }



    private Intent intent;
    public void putIntent(Intent intent){
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void jumpToTargetActivity(Context context){

        context.startActivity(intent);
        this.intent =null;
    }
}
