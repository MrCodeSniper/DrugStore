package com.example.android.chaoshi.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * basefragment
 * 
 * @author blue
 */
public abstract class BaseFragment extends Fragment {
	private Context context;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity;
	}




	private View mView;//缓存Fragment view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mView == null && getActivity() != null)//如果是fragment第一次初始化
		{
			mView = inflater.inflate(getLayoutId(), container, false);
			if (savedInstanceState != null)
				onRestoreInstanceState(savedInstanceState);
			x.view().inject(this,mView);
			initParams();
		}
		 else if (mView != null)
		{
			ViewGroup parent = (ViewGroup) mView.getParent();
			if (parent != null)
			{
				Log.e("tazzz","remove了parent");
				parent.removeView(mView);
			}else {
				Log.e("tazzz","没有removeparent");
			}

		}
		return mView;
	}

	protected abstract int getLayoutId();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected abstract void initParams();


	/**
	 * 恢复状态
	 *
	 * @author blue
	 */
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
	}
}
