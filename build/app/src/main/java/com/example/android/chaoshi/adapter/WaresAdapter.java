package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.net.Uri;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.BaseViewHolder;
import com.example.android.chaoshi.constant.Constant;
import com.example.android.chaoshi.entity.Wares;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;


/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class WaresAdapter extends SimpleAdapter<Wares> {



    public WaresAdapter(Context context, List<Wares> datas) {
        super(context, R.layout.template_grid_wares, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Wares item) {

        viewHoder.getTextView(R.id.text_title).setText(item.getName());
        viewHoder.getTextView(R.id.text_price).setText("￥"+item.getPrice());
        ImageLoader.getInstance().displayImage(item.getImgUrl(),viewHoder.getImageView(R.id.drawee_view), Constant.options);

    }



}
