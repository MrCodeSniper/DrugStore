package com.example.android.chaoshi.util;

import android.content.Context;
import android.text.TextUtils;

import com.example.android.chaoshi.bean.user.User_New;
import com.example.android.chaoshi.global.Consts;




/**
 * ProjectName:YayaShop
 * Autor： <a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * <p/>
 *
 */
public class UserLocalData {





    public static void putUser(Context context,User_New user){


        String user_json =  JSONUtil.toJSON(user);
        PreferencesUtils.putString(context, Consts.USER_JSON,user_json);

    }

    public static void putToken(Context context,String token){

        PreferencesUtils.putString(context, Consts.TOKEN,token);
    }


    public static User_New getUser(Context context){

        String user_json= PreferencesUtils.getString(context,Consts.USER_JSON);
        if(!TextUtils.isEmpty(user_json)){

            return  JSONUtil.fromJson(user_json,User_New.class);
        }
        return  null;
    }

    public static  String getToken(Context context){

        return  PreferencesUtils.getString( context,Consts.TOKEN);

    }


    public static void clearUser(Context context){


        PreferencesUtils.putString(context, Consts.USER_JSON,"");

    }

    public static void clearToken(Context context){

        PreferencesUtils.putString(context, Consts.TOKEN,"");
    }



}
