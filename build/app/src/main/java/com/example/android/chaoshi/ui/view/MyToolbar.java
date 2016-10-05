package com.example.android.chaoshi.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.ui.activity.MainActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/10/1.
 */

public class MyToolbar extends Toolbar {


    private LayoutInflater mInflater;

    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private CircleImageView mRightImageButton;
    private CircleImageView mleftImage;

    private Context context;


    public MyToolbar(Context context) {
        this(context,null);
    }



    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
        setContentInsetsRelative(10,10);




        if(attrs !=null) {
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.MyToolbar, defStyleAttr, 0);


            final Drawable rightIcon = a.getDrawable(R.styleable.MyToolbar_rightButtonIcon);
            if (rightIcon != null) {
                //setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }


            boolean isShowSearchView = a.getBoolean(R.styleable.MyToolbar_isShowSearchView,false);

            if(isShowSearchView){

                showSearchView();
                hideTitleView();

            }


            a.recycle();
        }

}

    public void  setRightButtonIcon(Drawable icon){

        if(mRightImageButton !=null){

            mRightImageButton.setImageDrawable(icon);
            mRightImageButton.setVisibility(VISIBLE);
        }

    }


    public  void setRightButtonOnClickListener(OnClickListener li){

        mRightImageButton.setOnClickListener(li);
    }



    private void initView() {


        if (mView == null) {

            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar_me, null);

            mleftImage = (CircleImageView) mView.findViewById(R.id.toolbar_leftbutton);
            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mRightImageButton = (CircleImageView) mView.findViewById(R.id.toolbar_rightButton);




            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);

            addView(mView, lp);
        }
    }


    @Override
    public void setTitle(@StringRes int resId) {
        super.setTitle(resId);
        setTitle(getContext().getText(resId));
    }


    @Override
    public void setTitle(CharSequence title) {
        initView();
        if(mTextTitle!=null){
            mTextTitle.setText(title);
            showTitleView();
        }

    }






    public  void showSearchView(){

        if(mSearchView !=null)
            mSearchView.setVisibility(VISIBLE);

    }


    public void hideSearchView(){
        if(mSearchView !=null)
            mSearchView.setVisibility(GONE);
    }

    public void showTitleView(){
        if(mTextTitle !=null)
            mTextTitle.setVisibility(VISIBLE);
    }


    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);

    }
}
