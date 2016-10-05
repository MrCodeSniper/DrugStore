package com.example.android.chaoshi.ui.fragment;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.BaseTabFragment;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.bean.shop.ItemTitlePager;
import com.example.android.chaoshi.constant.Matherd;
import com.example.android.chaoshi.ui.activity.LoginActivity;
import com.example.android.chaoshi.ui.activity.MainActivity;
import com.example.android.chaoshi.ui.activity.MuliActivity;
import com.example.android.chaoshi.ui.activity.Search2Activity;
import com.example.android.chaoshi.ui.activity.WebViewActivity;
import com.example.android.chaoshi.ui.adapter.HomeAdapter;
import com.example.android.chaoshi.util.StringUtils;
import com.example.android.chaoshi.util.UiUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

/**
 * Created by Android on 2016/9/6.
 */
public class HomeFragment extends BaseTabFragment {

    public boolean isUp;
    @ViewInject(R.id.rv_home)
    RecyclerView rvHome;
    @ViewInject(R.id.ib_foot)
    ImageButton ibFoot;
    @ViewInject(R.id.ib_top)
    ImageButton ibTop;
    @ViewInject(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.rl_toolbar)
    RelativeLayout rl_toolbar;

    @ViewInject(R.id.toolbar_searchview)
    EditText editText;



    @ViewInject(R.id.toolbar_leftbutton)
    private CircleImageView circleImageView;

    private HomeAdapter adapter;
    private LinearLayoutManager manager;
    private List<ItemTitlePager> newItemTitlePagers = new ArrayList<>();
    private List<ItemDangDang> newTodayPriceShops = new ArrayList<>();
    private List<ItemDangDang> newRoadShops = new ArrayList<>();
    private List<ItemDangDang> newGroupBuyingShops = new ArrayList<>();
    private List<ItemDangDang> newRecommendShops = new ArrayList<>();
    private List<ItemDangDang> newLikeShops = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initParams() {

        initRecycleView();

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_blue_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new MyOnRefreshListener());

        initData();


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BmobUser.getCurrentUser()==null){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    ((MainActivity) getActivity()).mTabHost.setCurrentTab(4);
                }
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Search2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        final AlertDialog dialog = new SpotsDialog(getActivity(), "请稍等...");
//        dialog.show();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    List<ItemTitlePager> itemTitlePagers = Matherd.getTitlePagerList();
                    for (ItemTitlePager itemTitlePager : itemTitlePagers) {
                        if (!StringUtils.isEmpty(itemTitlePager.img) && !StringUtils.isEmpty(itemTitlePager.href) && itemTitlePager.img.contains("_y.jpg")) {
                            newItemTitlePagers.add(itemTitlePager);
                        }
                    }
                    List<ItemDangDang> todayPriceShops = Matherd.getDangDangList1();
                    for (ItemDangDang itemDangDang : todayPriceShops) {
                        if (!StringUtils.isEmpty(itemDangDang.itemShopName) && !StringUtils.isEmpty(itemDangDang.itemShopProce)) {
                            itemDangDang.itemShopName = itemDangDang.itemShopName.trim();
                            if (itemDangDang.itemShopName.contains("定价")) {
                                String itemShopProce = itemDangDang.itemShopProce;
                                String discountPrice = itemShopProce.trim().substring(itemShopProce.indexOf('￥') + 2, itemShopProce.indexOf('定'));
                                String originalCost = null;
                                if (itemShopProce.contains("(")) {
                                    originalCost = itemShopProce.trim().substring(itemShopProce.indexOf('：') + 2, itemShopProce.indexOf('('));
                                } else {
                                    originalCost = itemShopProce.trim().substring(itemShopProce.indexOf('：') + 2, itemShopProce.length());
                                }
                                itemDangDang.discountPrice = discountPrice;
                                itemDangDang.originalCost = originalCost;
                                newTodayPriceShops.add(itemDangDang);
                            }
                        }
                    }

                    List<ItemDangDang> roadShop = Matherd.getDangDangList2();

                    for (int i = 0; i < roadShop.size(); i++) {
                        ItemDangDang itemDangDang = roadShop.get(i);
                        if (!StringUtils.isEmpty(itemDangDang.itemShopName) && !StringUtils.isEmpty(itemDangDang.itemShopProce)) {
                            String itemShopName = itemDangDang.itemShopName;
                            itemDangDang.itemShopName = itemShopName.substring(itemShopName.indexOf(']') + 1).trim();
                            String itemShopProce = itemDangDang.itemShopProce;
                            itemDangDang.originalCost = itemShopProce.substring(1);
                            newRoadShops.add(itemDangDang);
                        }
                    }
                    List<ItemDangDang> groupBuyingShops = Matherd.getDangDangList3();
                    for (int i = 0; i < groupBuyingShops.size(); i++) {
                        ItemDangDang itemDangDang = groupBuyingShops.get(i);
                        if (!StringUtils.isEmpty(itemDangDang.itemShopName) && !StringUtils.isEmpty(itemDangDang.itemShopProce)) {
                            String itemShopName = itemDangDang.itemShopName;
                            itemDangDang.itemShopName = itemShopName.trim();
                            String itemShopProce = itemDangDang.itemShopProce;
                            itemDangDang.originalCost = itemShopProce.trim().substring(1);
                            newGroupBuyingShops.add(itemDangDang);
                        }
                    }
                    List<ItemDangDang> recommendShops = Matherd.getDangDangList4();
                    for (int i = 0; i < recommendShops.size(); i++) {
                        ItemDangDang itemDangDang = recommendShops.get(i);
                        if (!StringUtils.isEmpty(itemDangDang.itemShopName) && !StringUtils.isEmpty(itemDangDang.itemShopProce)) {
                            String itemShopName = itemDangDang.itemShopName;
                            itemDangDang.itemShopName = itemShopName.trim();
                            String itemShopProce = itemDangDang.itemShopProce;
                            itemDangDang.originalCost = itemShopProce.trim().substring(1);
                            newRecommendShops.add(itemDangDang);
                        }
                    }
                    List<ItemDangDang> likeShops = Matherd.getDangDangList5();
                    for (int i = 0; i < likeShops.size(); i++) {
                        ItemDangDang itemDangDang = likeShops.get(i);
                        if (!StringUtils.isEmpty(itemDangDang.itemShopName) && !StringUtils.isEmpty(itemDangDang.itemShopProce)) {
                            String itemShopName = itemDangDang.itemShopName;
                            itemDangDang.itemShopName = itemShopName.trim().substring(itemShopName.indexOf(']') + 1);
                            String itemShopProce = itemDangDang.itemShopProce;
                            itemDangDang.originalCost = itemShopProce.trim().substring(1);
                            newLikeShops.add(itemDangDang);
                        }
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataChanged();
//                dialog.dismiss();
            }
        }.execute();
    }

    private void initRecycleView() {
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHome.setLayoutManager(manager);
        adapter = new HomeAdapter(this, getActivity(), newItemTitlePagers, newTodayPriceShops, newRoadShops, newGroupBuyingShops, newRecommendShops, newLikeShops, new MyOnCategoryClickCallback(), new MyOnItemClickCallback(), new MyOnShoppingCartClickCallBack());
        rvHome.setAdapter(adapter);
        rvHome.addOnScrollListener(new MyOnScrollListener());
    }

    @Event(value = {R.id.ib_top, R.id.ib_foot})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_top:
                rvHome.smoothScrollToPosition(0);
                break;
            case R.id.ib_foot:
                rvHome.smoothScrollToPosition(adapter.getItemCount() - 1);
                break;
        }
    }

    public void upAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, UiUtils.dp2px(-50));
        animator.setDuration(1000);
        animator.setTarget(ibFoot);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ibFoot.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        ibTop.setVisibility(View.VISIBLE);
        isUp = true;
    }

    public void downAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(UiUtils.dp2px(-50), 0);
        animator.setDuration(1000);
        animator.setTarget(ibFoot);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ibFoot.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        ibTop.setVisibility(View.GONE);
        isUp = false;
    }

    public interface OnItemClickCallback {

        void onItemClick(ItemDangDang itemDangDang);
    }

    public interface OnShoppingCartClickCallBack {

        void onShoppingCartClick(ItemDangDang itemDangDang);
    }

    public interface OnCategoryClickCallback {
        void onCategroyClick(String url);
    }

    /**
     * 点击分类
     */
    private class MyOnCategoryClickCallback implements OnCategoryClickCallback {
        @Override
        public void onCategroyClick(String url) {
            if (url.length() == 0) {
                startActivity(new Intent(getActivity(), MuliActivity.class));
                return;
            }
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    /**
     * 点击商品
     */
    private class MyOnItemClickCallback implements OnItemClickCallback {

        @Override
        public void onItemClick(ItemDangDang itemDangDang) {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("itemBean", itemDangDang);
            startActivity(intent);
        }
    }

    /**
     * 点击购物车
     */
    private class MyOnShoppingCartClickCallBack implements OnShoppingCartClickCallBack {
        @Override
        public void onShoppingCartClick(ItemDangDang itemDangDang) {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("itemBean", itemDangDang);
            startActivity(intent);
        }
    }


    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(3000);
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
//                                adapter.notifyDataChanged();
                                adapter.PullToRefresh();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }

    private class MyOnScrollListener extends RecyclerView.OnScrollListener {


        private int totalDy = 0;

        public MyOnScrollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (adapter.getGridLayoutManager() != null) {
                }
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            totalDy -= dy;


            if((-totalDy)>=UiUtils.dp2px(100)){
                //子view完全显示
                rl_toolbar.setBackgroundColor(getResources().getColor(R.color.white));
            }else {
                rl_toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }
    }
}
