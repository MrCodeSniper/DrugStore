package com.example.android.chaoshi.adapter;

import android.content.Context;


import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.BaseViewHolder;
import com.example.android.chaoshi.bean.CategoryNew;

import java.util.List;


/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class CategoryLeftAdapter extends SimpleAdapter<CategoryNew> {


    public CategoryLeftAdapter(Context context, List<CategoryNew> datas) {
        super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, CategoryNew item) {


        viewHoder.getTextView(R.id.textView).setText(item.getName());

    }
}
