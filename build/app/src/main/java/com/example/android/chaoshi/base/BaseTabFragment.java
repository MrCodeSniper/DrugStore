package com.example.android.chaoshi.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * basefragment
 * 
 * @author blue
 */
public abstract class BaseTabFragment extends BaseFragment {


	private View rootView;//缓存Fragment view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		   //缓存
			if(rootView==null){
				rootView= inflater.inflate(getLayoutId(),null);
			}
			//缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null) {
				parent.removeView(rootView);
			}
		x.view().inject(this,rootView);
		initParams();
		return rootView;
	}

	protected abstract int getLayoutId();

	protected abstract void initParams();





}
