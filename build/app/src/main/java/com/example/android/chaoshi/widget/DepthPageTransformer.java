package com.example.android.chaoshi.widget;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class DepthPageTransformer implements PageTransformer {

	private static final float MIN_SCALE = 0.75f;
	private BigDecimal rotate =new BigDecimal(180);
	private BigDecimal bPosition;
	@Override
	public void transformPage(View view, float position) {
		int pageWith=view.getWidth();
		if (position<-1) {
			view.setAlpha(1);
		}
		else if(position<0){
			view.setAlpha(1+position);
			view.setTranslationX(0);
			float scaleFactor =MIN_SCALE+(1-MIN_SCALE)*(1-Math.abs(position));
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
//			view.setRotation(180*position);
//			view.setRotationX(0.5f);
//			view.setRotationY(0.5f);
		}else if (position==0) {
			view.setAlpha(1);
			view.setTranslationX(0);
			view.setScaleX(1);
			view.setScaleY(1);
//			view.setRotation(0);
//			view.setRotationX(0.5f);
//			view.setRotationY(0.5f);
		}else if (position<=1) {
			view.setAlpha(1-position);
			view.setTranslationX(pageWith*-position);
			float scaleFactor =MIN_SCALE+(1-MIN_SCALE)*(1-Math.abs(position));
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
//			view.setRotation(180*(position));
//			view.setRotationX(0.5f);
//			view.setRotationY(0.5f);
		}else {
			view.setAlpha(0);
		}
	}

}
