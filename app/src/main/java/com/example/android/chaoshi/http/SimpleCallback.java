package com.example.android.chaoshi.http;

import android.content.Context;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.util.ToastUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;




/**
 *
 */
public abstract class SimpleCallback<T> extends BaseCallback<T> {

    protected Context mContext;

    public SimpleCallback(Context context){

        mContext = context;

    }

    @Override
    public void onBeforeRequest(Request request) {

    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    public void onResponse(Response response) {

    }

    @Override
    public void onTokenError(Response response, int code) {
        ToastUtils.makeText(mContext, mContext.getString(R.string.token_error));

//        Intent intent = new Intent();
//        intent.setClass(mContext, LoginActivity.class);
//        mContext.startActivity(intent);
//
//        MyApplication.getInstance().clearUser();

    }


}
