package com.example.android.chaoshi.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by CWQ on 2016/9/14.
 */
public class LikeShopRecyclerView extends RecyclerView {

    public LikeShopRecyclerView(Context context) {
        super(context);
    }

    public LikeShopRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LikeShopRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
