package com.example.android.chaoshi.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.ui.dialog.CenterBottomDialog;
import com.example.android.chaoshi.ui.tabhost.MainTab;
import com.example.android.chaoshi.ui.tabhost.OnTabReselectListener;
import com.example.android.chaoshi.ui.view.MyFragmentTabHost;
import com.example.android.chaoshi.ui.view.MyToolbar;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener, View.OnTouchListener {

    @ViewInject(android.R.id.tabhost)
    public MyFragmentTabHost mTabHost;

    @ViewInject(R.id.toolbar)
    public MyToolbar mytoolbar;

//    @ViewInject(R.id.drawerlayout)
//    private DrawerLayout drawerlayout;

//    @ViewInject(R.id.tv_title)
//    private TextView tv_title;
    @ViewInject(R.id.rl)
    private RelativeLayout rl;
//    @ViewInject(R.id.iv_icon)
//    private ImageView iv_icon;
    //    private ActionBarDrawerToggle mMDrawerToggle;
    private boolean isOpen = false;


    @ViewInject(R.id.quick_option_iv)
    View mAddBt;
    private CenterBottomDialog mCenterDiaLog;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


//        inittoolbar();


        mTabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        inittab();
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);

//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                int id = item.getItemId();
//                if (id == R.id.action_search) {
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .add(android.R.id.content, new FragmentRevealExample(), "fragment_my")
//                            .addToBackStack("fragment:reveal")
//                            .commit();
//                    return true;
//                }
//                return false;
//            }
//        });

    }

    @Override
    protected void initData() {
        mCenterDiaLog = new CenterBottomDialog(this);
        findViewById(R.id.quick_option_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCenterDiaLog.isShowing()) {
                    mCenterDiaLog.dismiss();
                } else {
                    mCenterDiaLog.show();
                }
            }
        });
    }


    @Event(value = {R.id.iv_icon, R.id.tv_title})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
    }

    public void setBackground() {
        rl.setBackgroundColor(getResources().getColor(R.color.tab_text));
        Log.e("tazzz", "设置了背景");
    }

    public void cacelBackground() {
        rl.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


//    private void inittoolbar() {
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
////        toolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
//        ActionBar actionBar = getSupportActionBar();
//
////        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                //这里写导航按钮点击处理的时间
////                //  onBackPressed();
////                Toast.makeText(MainActivity.this,"setNavigationOnClickListener",Toast.LENGTH_SHORT).show();
////            }
////        });
//
////        mMDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close) {
////            @Override
////            public void onDrawerOpened(View drawerView) {
////                super.onDrawerOpened(drawerView);
////         isOpen=true;
////            }
////
////            @Override
////            public void onDrawerClosed(View drawerView) {
////                super.onDrawerClosed(drawerView);
////                isOpen=false;
////
////            }
////        };
////        mMDrawerToggle.syncState();
////        drawerlayout.setDrawerListener(mMDrawerToggle);//设置监听器
//        actionBar.setDefaultDisplayHomeAsUpEnabled(false);
//    }


    private void inittab() {
        MainTab[] tabs = MainTab.values();
        int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = View.inflate(this, R.layout.tab_item_view, null);
            TextView title = (TextView) indicator.findViewById(R.id.tv_item_tab);
            ImageView icon = (ImageView) indicator.findViewById(R.id.iv_item_tab);

            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
                mTabHost.setNoTabChangedTag(getString(mainTab.getResName()));
            }
//            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            title.setText(getString(mainTab.getResName()));
//           indicator.set
            tab.setIndicator(indicator);


            tab.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }

            });
            mTabHost.getTabWidget().setDividerDrawable(null);
            mTabHost.addTab(tab, mainTab.getClz(), null);
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }


    @Override
    public void onTabChanged(String tabID) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        boolean consumed = false;
        // use getTabHost().getCurrentTabView to decide if the current tab is
        // touched again
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && v.equals(mTabHost.getCurrentTabView())) {
            // use getTabHost().getCurrentView() to get a handle to the view
            // which is displayed in the tab - and to get this views context
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.OnTabHostReselect();
                consumed = true;
            }
        }
        return consumed;
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabHost.getCurrentTabTag());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
