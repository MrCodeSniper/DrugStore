package com.example.android.chaoshi.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.ProjectUtil;
import com.example.android.chaoshi.constant.Constant;
import com.example.android.chaoshi.entity.Wares;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3.
 */

public class WareAdapter extends BaseQuickAdapter<Wares> {


    public WareAdapter(int layoutResId, List<Wares> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wares item) {
        helper.setText(R.id.text_title, item.getName())
                .setText(R.id.text_price, "￥"+item.getPrice())
        ;

        ImageLoader.getInstance().displayImage(item.getImgUrl(),(ImageView) helper.getView(R.id.drawee_view), Constant.options);

    }



}
