package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.BaseViewHolder;
import com.example.android.chaoshi.constant.Constant;
import com.example.android.chaoshi.entity.Wares;
import com.example.android.chaoshi.global.Consts;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;




/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class WareOrderAdapter extends SimpleAdapter<Wares> {


private Context context;

    public WareOrderAdapter(Context context, List<Wares> datas) {
        super(context, R.layout.template_order_wares, datas);
        this.context=context;

    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final Wares item) {

//        viewHoder.getTextView(R.id.text_title).setText(item.getName());
//        viewHoder.getTextView(R.id.text_price).setText("￥"+item.getPrice());
        ImageView draweeView = (ImageView) viewHoder.getView(R.id.drawee_view);
        ImageLoader.getInstance().displayImage(item.getImgUrl(),draweeView, Constant.options);

    }


//    public float getTotalPrice(){
//
//        float sum=0;
//        if(!isNull())
//            return sum;
//
//        for (ShoppingCart cart:
//                datas) {
//
//                sum += cart.getCount()*cart.getPrice();
//        }
//
//        return sum;
//
//    }


    private boolean isNull(){

        return (datas !=null && datas.size()>0);
    }






}
