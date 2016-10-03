package com.example.android.chaoshi.util;

import android.view.View;
import android.view.animation.CycleInterpolator;

import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * Created by CWQ on 2016/9/18.
 */
public class Tools {

    public static void shakeAimation(View view) {
        ViewPropertyAnimator.animate(view).translationXBy(UiUtils.dp2px(5)).setInterpolator(new CycleInterpolator(3)).setDuration(500).start();
    }
}
