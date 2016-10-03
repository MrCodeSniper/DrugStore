package com.example.android.chaoshi.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.ui.view.HackyViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import uk.co.senab.photoview.PhotoView;

public class BannerActivity extends AppCompatActivity {

    @ViewInject(R.id.viewpager)
    HackyViewPager viewPager;

    private int[] bannerPics = new int[]{R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4, R.mipmap.banner_5, R.mipmap.banner_6, R.mipmap.banner_7, R.mipmap.banner_8, R.mipmap.banner_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        int position = getIntent().getIntExtra("position", 0);

        x.view().inject(this);

        viewPager.setAdapter(new BannerAdapter());
    
        viewPager.setCurrentItem(position);
    }

    private class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return bannerPics.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(BannerActivity.this);
            photoView.setImageResource(bannerPics[position]);
            container.addView(photoView);
            return photoView;
        }
    }
}
