package com.example.android.chaoshi.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.chaoshi.bean.shop.Banner;
import com.example.android.chaoshi.bean.shop.ItemTitlePager;

import java.util.List;

/**
 * Created by CWQ on 2016/9/7.
 */
public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<Banner> banners;
    private List<ImageView> imageViews;
    private List<ItemTitlePager> itemTitlePagers;

    public BannerAdapter(Context context, List<Banner> banners, List<ImageView> imageViews) {
        this.context = context;
        this.banners = banners;
        this.imageViews = imageViews;
    }

//    public BannerAdapter(Context context, List<ItemTitlePager> itemTitlePagers, List<ImageView> imageViews) {
//        this.context = context;
//        this.itemTitlePagers = itemTitlePagers;
//        this.imageViews = imageViews;
//    }

    @Override
    public int getCount() {
        return banners.size();
//        return itemTitlePagers.size();
//        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        position = position % banners.size();
        ImageView imageView = imageViews.get(position);
        imageView.setOnClickListener(new InnerOnClickListener(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        position = position % banners.size();
        ImageView imageView = imageViews.get(position);
        container.removeView(imageView);
    }

    private class InnerOnClickListener implements View.OnClickListener {

        private int position;

        public InnerOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, BannerActivity.class);
//            intent.putExtra("position", position);
//            context.startActivity(intent);
        }
    }
}
