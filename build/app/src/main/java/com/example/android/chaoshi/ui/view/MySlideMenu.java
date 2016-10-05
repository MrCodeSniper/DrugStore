package com.example.android.chaoshi.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MySlideMenu extends ViewGroup {
	public static final int SCREEN_MENU = 1;
	public static final int SCREEN_MAIN = 0;
	private static final int SCREEN_INVALID = -1;
	
	private int mCurrentScreen;
	private int mNextScreen = SCREEN_INVALID;

	private Scroller mScroller;
	
	private final static int TOUCH_STATE_REST = 0;

	public int mTouchState = TOUCH_STATE_REST;

	public MySlideMenu(Context context) {
		this(context, null, 0);
	}

	public MySlideMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MySlideMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mScroller = new Scroller(getContext());
		mCurrentScreen = SCREEN_MAIN;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		measureViews(widthMeasureSpec, heightMeasureSpec);
		measureChildren(widthMeasureSpec, heightMeasureSpec);
	}

	public void measureViews(int widthMeasureSpec, int heightMeasureSpec) {
		View menuView = getChildAt(0);
		menuView.measure(menuView.getLayoutParams().width + menuView.getLeft()
				+ menuView.getRight(), heightMeasureSpec);

		View contentView = getChildAt(1);
		contentView.measure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = getChildCount();
		if (childCount != 2) {
			throw new IllegalStateException(
					"The childCount of SlidingMenu must be 2");
		}

		View menuView = getChildAt(0);
		final int width = menuView.getMeasuredWidth();
		menuView.layout(0, 0, width, menuView.getMeasuredHeight());

		View contentView = getChildAt(1);
		contentView.layout(width, 0, width+contentView.getMeasuredWidth(),
				contentView.getMeasuredHeight());
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		View child;
		for (int i = 0; i < getChildCount(); i++) {
			child = getChildAt(i);
			child.setFocusable(true);
			child.setClickable(true);
		}
	}
	

	
	void enableChildrenCache() {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View layout = (View) getChildAt(i);
			layout.setDrawingCacheEnabled(true);
		}
	}

	void clearChildrenCache() {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View layout = (View) getChildAt(i);
			layout.setDrawingCacheEnabled(false);
		}
	}
	
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
		} else if (mNextScreen != SCREEN_INVALID) {
			mCurrentScreen = Math.max(0,
					Math.min(mNextScreen, getChildCount() - 1));
			mNextScreen = SCREEN_INVALID;
			clearChildrenCache();
		}
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
		postInvalidate();
	}

	
	
	
	protected void snapToScreen(int whichScreen) {

		enableChildrenCache();

		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		boolean changingScreens = whichScreen != mCurrentScreen;

		mNextScreen = whichScreen;

		View focusedChild = getFocusedChild();
		if (focusedChild != null && changingScreens
				&& focusedChild == getChildAt(mCurrentScreen)) {
			focusedChild.clearFocus();
		}

		final int newX = (whichScreen ) * getChildAt(1).getWidth();
		final int delta = newX - getScrollX();
		Log.d("hua", "getScrollX="+getScrollX()+"------delta="+delta);
		mScroller.startScroll(getScrollX(), 0, delta, 0, 350);
		invalidate();
	}


	
	public int getCurrentScreen() {
		return mCurrentScreen;
	}
	
	public boolean isMainScreenShowing() {
		return mCurrentScreen == SCREEN_MAIN;
	}
	
	public void openMenu() {
		mCurrentScreen = SCREEN_MENU;
		snapToScreen(mCurrentScreen);
	}
	
	public void closeMenu() {
		mCurrentScreen = SCREEN_MAIN;
		snapToScreen(mCurrentScreen);
	}
	
	
	
}
