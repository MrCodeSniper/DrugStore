package com.example.android.chaoshi.util;

import android.content.Context;
import android.view.View;

import com.example.android.chaoshi.application.MyApplication;


/**
 * Created by CWQ on 2016/9/7.
 */
public class UiUtils {

    public static Context getContext() {
        return MyApplication.context;
    }

    public static int dp2px(float dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    public static float px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static View inflate(int resource) {
        return View.inflate(getContext(), resource, null);
    }
}
