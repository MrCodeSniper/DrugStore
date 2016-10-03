package com.example.android.chaoshi.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.chaoshi.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class GuideActivity extends AppCompatActivity {

    @ViewInject(R.id.viewpager)
    ViewPager viewPager;

    private boolean isEnter;

    private int[] imgs = new int[]{R.mipmap.ic_guide_first, R.mipmap.ic_guide_second, R.mipmap.ic_guide_third};
    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        x.view().inject(this);

        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgs[i]);
            imageViews.add(imageView);
        }
        MyPagerAdapter adapter = new MyPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }


    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1 && !isEnter) {
                viewPager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isEnter = true;
                        final AlertDialog dialog = new SpotsDialog(GuideActivity.this, "正在进入...");
                        dialog.show();
                        viewPager.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 3000);
                    }
                }, 500);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
