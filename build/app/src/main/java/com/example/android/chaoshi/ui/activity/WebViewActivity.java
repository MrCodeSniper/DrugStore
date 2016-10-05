package com.example.android.chaoshi.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.application.ProjectApp;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.roger.catloadinglibrary.CatLoadingView;

/**
 * Created by Administrator on 2016/9/21.
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private Button mAddShop;
    private Button mBuyShop;
    private CatLoadingView mDiaLog;
    private boolean isFirst = false;
    private ItemDangDang item;

    @Override
    protected void onLCreate() {
        initActionBar();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activitiy_webview;
    }

    @Override
    protected void initView() {
        isFirst = true;
        mDiaLog = new CatLoadingView();
        mDiaLog.show(getSupportFragmentManager(), "");
        mWebView = (WebView) findViewById(R.id.webview_);
        mAddShop = (Button) findViewById(R.id.add_commit);
        mBuyShop = (Button) findViewById(R.id.buy_commit);
        mAddShop.setOnClickListener(this);
        mBuyShop.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        try {
            Intent intent = getIntent();
            String url = intent.getStringExtra("url");
            if (url == null) {
                item = (ItemDangDang) intent.getSerializableExtra("itemBean");
                url = item.itemShopHref;
            }
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new InnerWebViewClient());
            mWebView.getSettings().setJavaScriptEnabled(true);
        } catch (Exception e) {
            finish();
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

    @Override
    public void onClick(View v) {
        mDiaLog.show(getSupportFragmentManager(), "正在加入购物车...");
        mDiaLog.dismiss();
        if (item == null) {
            return;
        }
        ProjectApp.addShopEntity(item);
    }

    private class InnerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return !isFirst;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mDiaLog.dismiss();
            super.onPageFinished(view, url);
        }
    }
}
