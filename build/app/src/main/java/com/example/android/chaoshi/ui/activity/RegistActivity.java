package com.example.android.chaoshi.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.Base2Fragment;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.ui.fragment.RegisteInputFragment;
import com.example.android.chaoshi.ui.fragment.RegisteMailFragment;
import com.example.android.chaoshi.ui.fragment.RegistePhoneFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/18.
 */
public class RegistActivity extends BaseActivity {
    private ViewPager mVpHome;
    private BottomNavigationBar mBottomNavigationBar;
    private ArrayList<Base2Fragment> mFragmentList = null;
    private InnerPagerAdapter mFragmentAdapter;
    private ArrayList<String> mTitleNames;
    private TextView mTvType;


    @Override
    protected void onLCreate() {
        initActionBar();
    }

    @Override
    public int getLayoutID() {
        return R.layout.user_register_layout;
    }

    @Override
    protected void initView() {
        mTitleNames = new ArrayList<String>();
        mTitleNames.add("在线注册");
        mTitleNames.add("手机注册");
        mTitleNames.add("邮箱注册");
        mFragmentList = new ArrayList<>();
        mVpHome = (ViewPager) findViewById(R.id.vp_home);
        mTvType = (TextView)findViewById(R.id.tv_regist_type);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_favorite, mTitleNames.get(0)))
                .addItem(new BottomNavigationItem(R.drawable.ic_gavel, mTitleNames.get(1)))
                .addItem(new BottomNavigationItem(R.drawable.ic_grade, mTitleNames.get(2)))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mVpHome.setCurrentItem(position);
                mTvType.setText(mTitleNames.get(position));
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        mFragmentList.add(new RegisteInputFragment());
        mFragmentList.add(new RegistePhoneFragment());
        mFragmentList.add(new RegisteMailFragment());

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
                mTvType.setText(mTitleNames.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mFragmentAdapter = new InnerPagerAdapter(getSupportFragmentManager());
        mVpHome.setAdapter(mFragmentAdapter);
      
    }

    @Override
    protected void initData() {

    }


    private class InnerPagerAdapter extends FragmentPagerAdapter {
        public InnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Base2Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleNames.get(position);
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("返回");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
