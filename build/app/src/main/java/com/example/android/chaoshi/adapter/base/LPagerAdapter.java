package com.example.android.chaoshi.adapter.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * LPagerAdapter 继承自android.support.v4.view.PagerAdapter
 *
 * @param <T> 传入此参数类型，以保证返回的数据为使用者需要的类型
 * @author 宋昌明
 */
public class LPagerAdapter<T extends View> extends PagerAdapter {

    private List<T> mViewList;

    public LPagerAdapter(List<T> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        T t = mViewList.get(position);
        container.addView(t);
        return t;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
