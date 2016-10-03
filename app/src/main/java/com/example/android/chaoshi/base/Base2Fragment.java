package com.example.android.chaoshi.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.chaoshi.application.ProjectApp;

/**
 * Created by Administrator on 2016/9/2.
 */
public abstract class Base2Fragment extends Fragment {
    private View mRootView;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        mRootView = inflater.inflate(bindLayout(), null);
        setInitView(mRootView);
        setInitData();
        return mRootView;
    }

    /**
     * 当绑定布局
     *
     * @return 返回当前fragment对应的布局
     */
    protected abstract int bindLayout();

    /**
     * 在mRootView中找各种id.....
     *
     * @param mRootView
     */
    protected abstract void setInitView(View mRootView);

    /**
     * 初始化数据，由于数据大部分是异步，所以。。。baseFragment就到这里了
     */
    protected abstract void setInitData();

    public Activity getlActivity() {
        return mActivity;
    }

    public View getRootView() {
        return mRootView;
    }

    /**
     * 显示Toast
     */
    public void toastShow(String content) {
        ProjectApp.getToast(content).show();
    }

}
