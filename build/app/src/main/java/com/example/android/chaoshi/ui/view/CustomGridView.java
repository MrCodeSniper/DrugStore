package com.example.android.chaoshi.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/9/13.
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        int expandSpec = MeasureSpec.makeMeasureSpec(
//                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }


    private float downY;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(getY()<downY){
                    isUp = true;
                }
                if(getY()>downY){
                    isUp = false;
                }
                break;
        }

        int[] location = new int[2];
        getLocationOnScreen(location);
        float y = ev.getRawY();
        int r = location[1];

        if(y>r&&y<r+getHeight()){
                getParent().requestDisallowInterceptTouchEvent(isRequest);

        }else{
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.onTouchEvent(ev);
    }

    private boolean isRequest = true;
    public void setRequest( boolean isReq){
        isRequest = isReq;
    }

    private boolean isUp = true;
    public boolean getIsUp(){
        return isUp;
    }
}
