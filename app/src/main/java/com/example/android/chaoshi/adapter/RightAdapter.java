package com.example.android.chaoshi.adapter;

import android.content.Context;

import com.example.android.chaoshi.entity.RightCategory;
import com.yuyh.easyadapter.abslistview.EasyLVAdapter;
import com.yuyh.easyadapter.abslistview.EasyLVHolder;

import java.util.List;

/**
 * Created by Android on 2016/9/9.
 */
public class RightAdapter extends EasyLVAdapter<RightCategory> {


    public RightAdapter(Context context, List<RightCategory> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, RightCategory category) {

    }
}
