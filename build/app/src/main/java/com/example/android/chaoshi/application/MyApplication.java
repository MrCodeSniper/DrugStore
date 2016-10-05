package com.example.android.chaoshi.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.android.chaoshi.bean.user.User_New;
import com.example.android.chaoshi.util.UserLocalData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.bmob.v3.Bmob;

/**
 * Created by Android on 2016/9/7.
 */
public class MyApplication extends Application {
    public static final int STATE_LOGOUT = 0;
    public static final int STATE_LOGIN = 1;
    public static Context context;
    public static int curState = STATE_LOGOUT;
    public static String username;
    public static String pwd;
    private Context applicationcontext;



    private User_New user;



    private static MyApplication mInstance;


    public static MyApplication getInstance(){

        return  mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationcontext = getApplicationContext();

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);

        Bmob.initialize(this, "3a4be38c7456063eb274888149dd7c60");

        context = getApplicationContext();

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
