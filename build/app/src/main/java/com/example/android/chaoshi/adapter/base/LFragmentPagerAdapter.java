package com.example.android.chaoshi.adapter.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * LPagerAdapter 继承自android.support.v4.view.PagerAdapter
 * 
 * @author 宋昌明
 * @param <T>
 *            传入此参数类型，以保证返回的数据为使用者需要的类型
 */
public class LFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

	private List<T> mViewList;
	
	public LFragmentPagerAdapter(FragmentManager fm,List<T> mViewList) {
		super(fm);
		this.mViewList = mViewList;
	}
	@Override
	public Fragment getItem(int arg0) {
		return mViewList.get(arg0);
	}
	@Override
	public int getCount() {
		return mViewList.size();
	}
	@Override
	public int getItemPosition(Object object) {
		return POSITION_UNCHANGED;
	}
	
}
