package com.example.android.chaoshi.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.RightCategory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Android on 2016/9/7.
 */
public class QuickAdapter extends BaseQuickAdapter<RightCategory>{

    public QuickAdapter(List<RightCategory> data) {
        super(R.layout.grid_item,data);
    }


    @Override
    protected void convert(BaseViewHolder helper, RightCategory category) {
        helper.setText(R.id.tv_text, category.title);
        ImageLoader.getInstance().displayImage(category.image.getFileUrl(),(ImageView)helper.getView(R.id.iv_icon));
    }
}
