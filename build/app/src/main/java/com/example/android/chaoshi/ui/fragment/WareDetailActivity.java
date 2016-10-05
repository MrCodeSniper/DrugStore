package com.example.android.chaoshi.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.application.ProjectApp;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.entity.Wares;
import com.example.android.chaoshi.global.Consts;
import com.example.android.chaoshi.ui.activity.CommitActivity;
import com.example.android.chaoshi.ui.activity.LoginActivity;
import com.example.android.chaoshi.util.ToastUtils;

import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2016/10/3.
 */
public class WareDetailActivity extends BaseActivity{

    @ViewInject(R.id.webview_detail)
    private WebView webView;
    @ViewInject(R.id.btn)
    private Button btn;
    private WebAppInterface webAppInterface;

    private SpotsDialog spotsDialog;
    private SpotsDialog spotsDialog2;



    private Wares wares;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_ware;
    }

    @Override
    protected void initView() {
         spotsDialog=new SpotsDialog(this,"正在加入购物车.....");
        spotsDialog2=new SpotsDialog(this,"正在载入网页......");
        spotsDialog2.show();
        Serializable serializableExtra = getIntent().getSerializableExtra(Consts.WARE);
        if(serializableExtra==null){
            this.finish();
        }else {
            wares=(Wares)serializableExtra;
        }

//        webView.loadUrl("file:///android_asset/index.html");

        webView.loadUrl(Consts.API.WARES_DETAIL);

        initWebSetting(webView.getSettings());



        webAppInterface = new WebAppInterface(this);
        //1.写接口让webview调
        webView.addJavascriptInterface(webAppInterface,"appInterface");
        webView.setWebViewClient(new MyWebClient());

//        webAppInterface.showDetail(wares);需要加载完



    }

    @Override
    protected void initData() {
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             webAppInterface.showname("陈鸿");
         }
     });
    }


    private void initWebSetting(WebSettings webSettings){
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);//阻塞图片 false
        webSettings.setAppCacheEnabled(true);
    }



    //自定义接口
    class WebAppInterface{

        private Context context;

        public WebAppInterface(Context context) {
            this.context=context;
        }

        @JavascriptInterface
        public void Hello(String name){
        btn.setText(name+":name");
        }

        @JavascriptInterface
        public void showname(final String name){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:showname('"+name+"')");
                }
            });
        }

        @JavascriptInterface
        public void showDetail(final Wares wares){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:showDetail('"+wares.getId()+"')");
                }
            });
        }

        @JavascriptInterface
        public void buy(long id){
         //

            if(BmobUser.getCurrentUser()==null){
                Intent intent=new Intent(WareDetailActivity.this,LoginActivity.class);
                startActivity(intent);
            }else {
                Intent intent=new Intent(WareDetailActivity.this,CommitActivity.class);
                intent.putExtra("ware",wares);
                startActivity(intent);
            }



        }

        @JavascriptInterface
        public void addFavorites(long id){
//            spotsDialog.show();
            ItemDangDang itemDangDang=new ItemDangDang();
            itemDangDang.itemShopImg=wares.getImgUrl();
            itemDangDang.discountPrice=wares.getPrice()+"";
            itemDangDang.itemShopName=wares.getName();
            itemDangDang.itemShopHref=wares.getDescription();
            ProjectApp.addShopEntity(itemDangDang);
//            spotsDialog.dismiss();
        }







    }



    class MyWebClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            webAppInterface.showDetail(wares);
            if(spotsDialog2!=null){
                spotsDialog2.dismiss();
            }


        }
    }

}
