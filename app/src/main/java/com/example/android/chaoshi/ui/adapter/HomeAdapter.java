package com.example.android.chaoshi.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daidingkang.SnapUpCountDownTimerView;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.bean.shop.Banner;
import com.example.android.chaoshi.bean.shop.Category;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.bean.shop.ItemTitlePager;
import com.example.android.chaoshi.bean.shop.Shop;
import com.example.android.chaoshi.ui.fragment.HomeFragment;
import com.example.android.chaoshi.ui.view.VerticalSwitchTextView;
import com.example.android.chaoshi.util.UiUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by CWQ on 2016/9/10.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_BANNER = 0;
    private final int TYPE_CATEGORY = 1;
    private final int TYPE_HOT_POINT = 2;
    private final int TYPE_TODAY_PRICE = 3;
    private final int TYPE_ROAD = 4;
    private final int TYPE_RECOMMEND = 5;
    private final int TYPE_ITEM = 6;
    @ViewInject(R.id.vp_banner)
    ViewPager vpBanner;
    //    @ViewInject(R.id.indicator1)
//    View indicator1;
//    @ViewInject(R.id.indicator2)
//    View indicator2;
//    @ViewInject(R.id.indicator3)
//    View indicator3;
//    @ViewInject(R.id.indicator4)
//    View indicator4;
//    @ViewInject(R.id.indicator5)
//    View indicator5;
//    @ViewInject(R.id.indicator6)
//    View indicator6;
//    @ViewInject(R.id.indicator7)
//    View indicator7;
//    @ViewInject(R.id.indicator8)
//    View indicator8;
//    @ViewInject(R.id.indicator9)
//    View indicator9;
    @ViewInject(R.id.rv_category)
    RecyclerView rvCategory;
    @ViewInject(R.id.vertical_switch_text_view)
    VerticalSwitchTextView verticalSwitchTextView;
    @ViewInject(R.id.timer_view)
    SnapUpCountDownTimerView snapUpCountDownTimerView;
    @ViewInject(R.id.fl_timer)
    FrameLayout flTimer;
    @ViewInject(R.id.rv_today_price_shop)
    RecyclerView rvTodayPriceShop;
    @ViewInject(R.id.rv_road_shop)
    RecyclerView rvRoadShop;
    @ViewInject(R.id.rv_group_buying_shop)
    RecyclerView rvGroupBuyingShop;
    @ViewInject(R.id.rv_recommend_shop)
    RecyclerView rvRecommendShop;
    @ViewInject(R.id.rv_like_shop)
    RecyclerView rvLikeShop;
    @ViewInject(R.id.tv_change)
    TextView tvChange;
    @ViewInject(R.id.ll_indicator)
    LinearLayout llIndicator;

    private Context context;
    private View bannerView;
    private List<Banner> banners = new ArrayList<>();
    private List<View> indications = new ArrayList<>();
    private int lastBannerPosition;
    private List<ImageView> imageViews = new ArrayList<>();
    private int[] bannerPics = new int[]{R.mipmap.banner_1_meitu_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4, R.mipmap.banner_5, R.mipmap.banner_6, R.mipmap.banner_7, R.mipmap.banner_8, R.mipmap.banner_9};
    private Handler handler;
    private List<Category> categories = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private List<String> laba = new ArrayList<>();
    private View timerView;
    private HomeFragment homeFragment;
    private GridLayoutManager gridLayoutManager;
    private BannerAdapter bannerAdapter;
    private HomeFragment.OnItemClickCallback onItemClickCallback;
    private List<Shop> todayPriceShops1 = new ArrayList<>();
    private List<Shop> todayPriceShops2 = new ArrayList<>();
    private List<List<Shop>> todayPriceShopsList = new ArrayList<>();
    //    private List<Shop> todayPriceShops = new ArrayList<>();
    private List<Shop> groupBuyingShops1 = new ArrayList<>();
    private List<Shop> groupBuyingShops2 = new ArrayList<>();
    private List<List<Shop>> groupBuyingShopsList = new ArrayList<>();
    //    private List<Shop> groupBuyingShops = new ArrayList<>();
    private List<Shop> recommendShops1 = new ArrayList<>();
    private List<Shop> recommendShops2 = new ArrayList<>();
    private List<List<Shop>> recommendShopsList = new ArrayList<>();
    //    private List<Shop> recommendShops = new ArrayList<>();
    private TodayPriceShopAdapter todayPriceShopAdapter;
    private int index;
    private GroupBuyingShopAdapter groupBuyingShopAdapter;
    private RecommendShopAdapter recommendShopAdapter;
    private LikeShopAdapter likeShopAdapter;
    private List<Shop> likeShops1 = new ArrayList<>();
    private List<Shop> likeShops2 = new ArrayList<>();
    private List<List<Shop>> likeShopsList = new ArrayList<>();
//    private List<Shop> likeShops = new ArrayList<>();
    private int likeShopIndex;
    private HomeFragment.OnShoppingCartClickCallBack onShoppingCartClickCallBack;
    private List<ItemTitlePager> itemTitlePagers;
    private List<ItemDangDang> todayPriceShops;
    private List<ItemDangDang> roadShops;
    private RoadShopAdapter roadShopAdapter;
    private List<ItemDangDang> groupBuyingShops;
    private List<ItemDangDang> recommendShops;
    private List<ItemDangDang> likeShops;
    private HomeFragment.OnCategoryClickCallback onCategoryClickCallback;

    public HomeAdapter(HomeFragment homeFragment, Context context, List<ItemTitlePager> itemTitlePagers, List<ItemDangDang> todayPriceShops, List<ItemDangDang> roadShops, List<ItemDangDang> groupBuyingShops, List<ItemDangDang> recommendShops,List<ItemDangDang> likeShops,HomeFragment.OnCategoryClickCallback onCategoryClickCallback, HomeFragment.OnItemClickCallback onItemClickCallback, HomeFragment.OnShoppingCartClickCallBack onShoppingCartClickCallBack) {

        this.homeFragment = homeFragment;
        this.context = context;
        this.onItemClickCallback = onItemClickCallback;
        this.onShoppingCartClickCallBack = onShoppingCartClickCallBack;
        this.itemTitlePagers = itemTitlePagers;
        this.todayPriceShops = todayPriceShops;
        this.roadShops = roadShops;
        this.groupBuyingShops = groupBuyingShops;
        this.recommendShops = recommendShops;
        this.likeShops=likeShops;
        this.onCategoryClickCallback = onCategoryClickCallback;

        initBanner();

        initSnapUpCountDownTimerView();

        initData();
        categoryAdapter = new CategoryAdapter(context, categories, onCategoryClickCallback);
        roadShopAdapter = new RoadShopAdapter(roadShops, onItemClickCallback);
        groupBuyingShopAdapter = new GroupBuyingShopAdapter(groupBuyingShops, onItemClickCallback);
        recommendShopAdapter = new RecommendShopAdapter(recommendShops, onItemClickCallback);
        likeShopAdapter = new LikeShopAdapter(context, likeShops, onItemClickCallback, onShoppingCartClickCallBack);
    }

    private void initSnapUpCountDownTimerView() {
        timerView = UiUtils.inflate(R.layout.view_snap_up_count_down_timer);
        x.view().inject(this, timerView);
        snapUpCountDownTimerView.setTime(2, 00, 00);
        snapUpCountDownTimerView.start();
    }

    private void initBanner() {

        bannerView = UiUtils.inflate(R.layout.home_banner);

        x.view().inject(this, bannerView);

        initBannerData();

        bannerAdapter = new BannerAdapter(context, banners, imageViews);
//        bannerAdapter = new BannerAdapter(context, itemTitlePagers, imageViews);
        vpBanner.setAdapter(bannerAdapter);

//        vpBanner.setCurrentItem(banners.size() * 1000);

        vpBanner.addOnPageChangeListener(new InnerOnPageChangeListener());

//        vpBanner.setOnTouchListener(new InnerOnTouchListener());

    }

    private void initData() {

        for (int i = 0; i < 12; i++) {
            todayPriceShops1.add(new Shop("海飞丝 去屑洗发露 200ml 丝质柔滑型", "¥24.9", "http://item.m.yhd.com/item/958494?tc=3.0.5.958494.1&tp=5008.%E6%B5%B7%E9%A3%9E%E4%B8%9D.185.0.36.LSd0XQ`-10-FBNor&ti=GR7B4b", "http://d11.yihaodianimg.com/N05/M08/4F/FF/CgQI0leRwfaAXTDSAAH3TuZ854I86701_640x640.jpg", R.mipmap.img_head_shoulders));
            todayPriceShops2.add(new Shop("飘柔 家庭护理 兰花长效洁顺 水润洗发露家庭装 1000ml", "¥30.9", "http://item.m.yhd.com/item/1209898?tc=3.0.5.1209898.1&tp=5008.%E9%A3%98%E6%9F%94.185.0.1.LSd4qpP-10-35AaM&ti=KV2WMa", "http://d10.yihaodianimg.com/t1/2012/0813/317/474/712566b59fe8efaeYY_640x640.jpg", R.mipmap.img_rejoice));

            groupBuyingShops1.add(new Shop("DELL 戴尔 Ins 15ER-1528B 15.6英寸 黑色 笔记本电脑 i5-5200U/4GB/500GB/NVIDIA GT 920M (2G)/DVDRW/Win10", "¥2949", "http://item.m.yhd.com/item/62030424", "http://d11.yihaodianimg.com/N08/M05/73/49/ChEi1Vci2t2AFbiQAAI2r7NmNNs56801_640x640.jpg", R.mipmap.img_dell));
            groupBuyingShops2.add(new Shop("Apple 苹果 MacBook Air MJVM2CH/A 11英寸 银色 笔记本电脑 - i5-1.6GHz/4G/128GB FLASH/集显", "¥5788", "http://item.m.yhd.com/item/45067249", "http://d10.yihaodianimg.com/V00/M05/0F/79/CgQDsFUI3OaAOxWrAAVKloZyKC422201_640x640.jpg", R.mipmap.img_apple));

            recommendShops1.add(new Shop("百草味 碧根果 奶油味 190g/袋 X 2", "¥31.6", "http://item.m.yhd.com/item/22649608", "http://d10.yihaodianimg.com/N07/M05/18/79/CgQIz1dyKNKAEA-MAAUz4nZ3rQw07101_640x640.jpg", R.mipmap.img_pecan));
            recommendShops2.add(new Shop("百草味 枣抱仁 新疆灰枣夹核桃仁 235g/袋", "¥26.9", "http://item.m.yhd.com/item/60653222", "http://d10.yihaodianimg.com/N06/M02/E3/C5/CgQIzleN8eiAeDY5AAW2xlZu3eA87201_640x640.jpg", R.mipmap.img_red_dates));

            likeShops1.add(new Shop("五芳斋 真空五芳大肉粽 1400g 5袋 140g*2只 嘉兴肉粽子端午特产 方便速食", "¥59.9", "http://item.m.yhd.com/item/19804960", "http://d10.yihaodianimg.com/N06/M01/6C/E9/CgQIzVU2Av6AMOanAAQBNvEXI5M32700_640x640.jpg", R.mipmap.img_rice_dumpling));
            likeShops2.add(new Shop("五芳斋 辣味酥饼8个 金华酥饼传统正宗糕点心梅干菜小吃零食80g", "¥15", "http://item.m.yhd.com/item/58731341", "http://d10.yihaodianimg.com/N08/M07/A3/D1/ChEi1FaNxFeAVkk1AANV5vxYaik17300_640x640.jpg", R.mipmap.img_green_bean_cake));
        }
        todayPriceShopsList.add(todayPriceShops1);
        todayPriceShopsList.add(todayPriceShops2);
//        todayPriceShops.addAll(todayPriceShops1);

        groupBuyingShopsList.add(groupBuyingShops1);
        groupBuyingShopsList.add(groupBuyingShops2);
//        groupBuyingShops.addAll(groupBuyingShops1);

        recommendShopsList.add(recommendShops1);
        recommendShopsList.add(recommendShops2);
//        recommendShops.addAll(recommendShops1);

        likeShopsList.add(likeShops1);
        likeShopsList.add(likeShops2);
//        likeShops.addAll(likeShops1);
    }

    private void initBannerData() {
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());

//        indications.add(indicator1);
//        indications.add(indicator2);
//        indications.add(indicator3);
//        indications.add(indicator4);
//        indications.add(indicator5);
//        indications.add(indicator6);
//        indications.add(indicator7);
//        indications.add(indicator8);
//        indications.add(indicator9);

        for (int i = 0; i < banners.size(); i++) {
            ImageView indicator = new ImageView(context);
            indicator.setImageResource(R.drawable.shape_indicator_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UiUtils.dp2px(8), UiUtils.dp2px(8));
            if (i == 0) {
                indicator.setImageResource(R.drawable.shape_indicator_checked);
            }
            if (i > 0) {
                params.leftMargin = UiUtils.dp2px(5);
            }
            indicator.setLayoutParams(params);
            llIndicator.addView(indicator);
        }

        for (int i = 0; i < banners.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(R.mipmap.banner_1);
            imageView.setImageResource(bannerPics[i]);
//            x.image().bind(imageView, itemTitlePagers.get(i).img);
            imageViews.add(imageView);
        }

//        bannerAdapter = new BannerAdapter(context, itemTitlePagers, imageViews);
//        vpBanner.setAdapter(bannerAdapter);
//        vpBanner.addOnPageChangeListener(new InnerOnPageChangeListener());

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    int currentItem = vpBanner.getCurrentItem() + 1;
//                currentItem = currentItem % banners.size();
//                    currentItem = currentItem % itemTitlePagers.size();
                    vpBanner.setCurrentItem(currentItem);
                    sendEmptyMessageDelayed(0, 5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.sendEmptyMessageDelayed(0, 5000);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerHolder(bannerView);
        } else if (viewType == TYPE_CATEGORY) {
            return new CategoryHolder(UiUtils.inflate(R.layout.home_category));
        } else if (viewType == TYPE_HOT_POINT) {
            return new HotPointHolder(UiUtils.inflate(R.layout.home_hot_point));
        } else if (viewType == TYPE_TODAY_PRICE) {
            return new TodayPriceHolder(UiUtils.inflate(R.layout.home_today_price));
        } else if (viewType == TYPE_ROAD) {
            return new RoadHolder(UiUtils.inflate(R.layout.home_road));
        } else if (viewType == TYPE_RECOMMEND) {
            return new RecommendHolder(UiUtils.inflate(R.layout.home_recommend));
        }
//        else {
//            return new ItemHolder(UiUtils.inflate(R.layout.recycler_item_home));
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= TYPE_ROAD) {
            if (!homeFragment.isUp) {
                homeFragment.upAnim();
            }
        } else {
            if (homeFragment.isUp) {
                homeFragment.downAnim();
            }

        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_CATEGORY;
        } else if (position == 2) {
            return TYPE_HOT_POINT;
        } else if (position == 3) {
            return TYPE_TODAY_PRICE;
        } else if (position == 4) {
            return TYPE_ROAD;
        } else if (position == 5) {
            return TYPE_RECOMMEND;
        }
//        else {
//            return TYPE_ITEM;
//        }
        return super.getItemViewType(position);
    }

    private void initCategory() {
        GridLayoutManager manager = new GridLayoutManager(context, 5);
        rvCategory.setLayoutManager(manager);

        initCategoryData();

        
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.addItemDecoration(new SpaceItemDecoration());
    }

    private void initCategoryData() {
        categories.add(new Category(R.mipmap.category_1_group, "1号团", "http://t.m.yhd.com/"));
        categories.add(new Category(R.mipmap.category_recharge, "充值中心", "http://m.yhd.com/charge/showH5VirtualChargeHome.action"));
        categories.add(new Category(R.mipmap.category_thunder_buy, "3C数码", ""));
        categories.add(new Category(R.mipmap.category_life, "活色生鲜", "http://cms.m.yhd.com/sale/qAxqCCfMqtr"));
        categories.add(new Category(R.mipmap.category_find_food, "进口美食", "http://cms.m.yhd.com/sale/OOkPPhUUjHB"));
        categories.add(new Category(R.mipmap.category_dodge_buy, "1号闪购", "http://m.yhd.com/mingpin/"));
        categories.add(new Category(R.mipmap.category_shop, "1号商城", "http://cms.m.yhd.com/sale/wBOQHIycIyO"));
        categories.add(new Category(R.mipmap.category_sea_buy, "1号海购", "http://chan.m.yhd.com/haigou"));
        categories.add(new Category(R.mipmap.category_new_product, "新品试用", "http://m.yhd.com/try/"));
        categories.add(new Category(R.mipmap.category_1_store, "1号店", "http://m.yhd.com/myH5/h5Index/index.do"));
    }

    public GridLayoutManager getGridLayoutManager() {
        if (gridLayoutManager == null) {
            return null;
        }
        return gridLayoutManager;
    }

    public void notifyDataChanged() {
//        initBannerData();
//        bannerAdapter.notifyDataSetChanged();
        try {
            todayPriceShopAdapter.notifyDataSetChanged();
            roadShopAdapter.notifyDataSetChanged();
            groupBuyingShopAdapter.notifyDataSetChanged();
            recommendShopAdapter.notifyDataSetChanged();
            likeShopAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        index = (index + 1) % 2;
//        todayPriceShops.clear();
//        todayPriceShops.addAll(todayPriceShopsList.get(index));
//        todayPriceShopAdapter.notifyDataSetChanged();
//        groupBuyingShops.clear();
//        groupBuyingShops.addAll(groupBuyingShopsList.get(index));
//        if (groupBuyingShopAdapter != null) {
//            groupBuyingShopAdapter.notifyDataSetChanged();
//        }
//        recommendShops.clear();
//        recommendShops.addAll(recommendShopsList.get(index));
//        if (recommendShopAdapter != null) {
//            recommendShopAdapter.notifyDataSetChanged();
//        }
    }

    public void PullToRefresh() {
        Collections.shuffle(todayPriceShops);
        todayPriceShopAdapter.notifyDataSetChanged();

        Collections.shuffle(roadShops);
        roadShopAdapter.notifyDataSetChanged();

        Collections.shuffle(groupBuyingShops);
        groupBuyingShopAdapter.notifyDataSetChanged();

        Collections.shuffle(recommendShops);
        recommendShopAdapter.notifyDataSetChanged();

        Collections.shuffle(likeShops);
        likeShopAdapter.notifyDataSetChanged();
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        public BannerHolder(View itemView) {
            super(itemView);
        }
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        public CategoryHolder(View itemView) {
            super(itemView);

            x.view().inject(HomeAdapter.this, itemView);

            initCategory();

        }

    }

    class HotPointHolder extends RecyclerView.ViewHolder {

        public HotPointHolder(View itemView) {
            super(itemView);

            x.view().inject(HomeAdapter.this, itemView);

            laba.add("每天逛一逛，发现新鲜好货");
            laba.add("每10分钟抽奖送iPhone");
            laba.add("9.9翼起去购物，翼支付首单满99立减30！");

            verticalSwitchTextView.setTextContent(laba);
        }
    }

    class TodayPriceHolder extends RecyclerView.ViewHolder {

        public TodayPriceHolder(View itemView) {
            super(itemView);

            x.view().inject(HomeAdapter.this, itemView);

            flTimer.addView(timerView);

            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvTodayPriceShop.setLayoutManager(manager);

            todayPriceShopAdapter = new TodayPriceShopAdapter(todayPriceShops, onItemClickCallback);
            rvTodayPriceShop.setAdapter(todayPriceShopAdapter);
            rvTodayPriceShop.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.HORIZONTAL));

        }
    }

    class RoadHolder extends RecyclerView.ViewHolder {

        public RoadHolder(View itemView) {
            super(itemView);

            x.view().inject(HomeAdapter.this, itemView);

            GridLayoutManager manager = new GridLayoutManager(context, 3);
//            StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            rvRoadShop.setLayoutManager(manager);
//            roadShopAdapter = new RoadShopAdapter(roadShops,onItemClickCallback);
            rvRoadShop.setAdapter(roadShopAdapter);
            rvRoadShop.addItemDecoration(new DividerItemDecoration(-1));
        }
    }

    class RecommendHolder extends RecyclerView.ViewHolder {

        public RecommendHolder(View itemView) {
            super(itemView);

            x.view().inject(HomeAdapter.this, itemView);

            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvGroupBuyingShop.setLayoutManager(manager);
//            groupBuyingShopAdapter = new GroupBuyingShopAdapter(groupBuyingShops, onItemClickCallback);
            rvGroupBuyingShop.setAdapter(groupBuyingShopAdapter);
            rvGroupBuyingShop.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.HORIZONTAL));

            manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvRecommendShop.setLayoutManager(manager);
//            recommendShopAdapter = new RecommendShopAdapter(recommendShops, onItemClickCallback);
            rvRecommendShop.setAdapter(recommendShopAdapter);
            rvRecommendShop.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.HORIZONTAL));

            gridLayoutManager = new GridLayoutManager(context, 3);
            rvLikeShop.setLayoutManager(gridLayoutManager);

            rvLikeShop.setAdapter(likeShopAdapter);
            rvLikeShop.addItemDecoration(new DividerItemDecoration(-1));
            rvLikeShop.addOnScrollListener(new MyOnScrollListener());

            tvChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new SpotsDialog(context, "请稍等...");
                    alertDialog.show();
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(2000);
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
//                                        likeShopIndex = (likeShopIndex + 1) % 2;
//                                        likeShops.clear();
//                                        likeShops.addAll(likeShopsList.get(likeShopIndex));
//                                        likeShopAdapter.notifyDataSetChanged();
                                        Collections.shuffle(likeShops);
                                        likeShopAdapter.notifyDataSetChanged();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            });
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);
        }
    }

    private class DividerItemDecoration extends RecyclerView.ItemDecoration {

        public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
        public static final int VERTICAL = LinearLayoutManager.VERTICAL;
        private Drawable divider;
        private int orientation;

        public DividerItemDecoration(int orientation) {
            super();
            this.orientation = orientation;
            TypedArray typedArray = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
            divider = typedArray.getDrawable(0);
            typedArray.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            if (orientation == HORIZONTAL) {
                drawHorizontal(c, parent);
            } else if (orientation == VERTICAL) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
                drawVertical(c, parent);
            }

        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, divider.getIntrinsicWidth(), 0);
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() - parent.getPaddingBottom();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + divider.getIntrinsicHeight();
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView v = new RecyclerView(parent.getContext());
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + divider.getIntrinsicHeight();
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }

    private class InnerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            position = position % banners.size();
//            indications.get(lastBannerPosition).setBackgroundResource(R.drawable.shape_indicator_normal);
//            indications.get(position).setBackgroundResource(R.drawable.shape_indicator_checked);
//            lastBannerPosition = position;
            ((ImageView) llIndicator.getChildAt(lastBannerPosition)).setImageResource(R.drawable.shape_indicator_normal);
            ((ImageView) llIndicator.getChildAt(position)).setImageResource(R.drawable.shape_indicator_checked);
            lastBannerPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class InnerOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.removeCallbacksAndMessages(null);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
//                    handler.sendEmptyMessageDelayed(0, 5000);
                    break;
            }
            return true;
        }
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        public SpaceItemDecoration() {
            super();

        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int itemCount = categoryAdapter.getItemCount();
            int pos = parent.getChildAdapterPosition(view);
            if (pos <= 4) {
                outRect.bottom = UiUtils.dp2px(15);
            }
        }
    }

    private class MyOnScrollListener extends RecyclerView.OnScrollListener {

        public MyOnScrollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}
