package com.example.android.chaoshi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.base.LBaseAdapterMore;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.base.ProjectUtil;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.constant.Matherd;
import com.example.android.chaoshi.widget.XListView;
import com.roger.catloadinglibrary.CatLoadingView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Search2Activity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, XListView.IXListViewListener {

    String url_3c_search = "http://search.dangdang.com/?key=";
    String url_3c_pager = "&page_index=";
    /**
     * 综合排序
     */
    String sort_default = "&sort_type=sort_default";
    /**
     * 销量排序
     */
    String sort_sale_amt_desc = "&sort_type=sort_sale_amt_desc";
    /**
     * 好评排序
     */
    String sort_score_desc = "&sort_type=sort_score_desc";
    /**
     * 最新排序
     */
    String sort_last_changed_date_desc = "&sort_type=sort_last_changed_date_desc";
    /**
     * 价格排序
     */
    String sort_xlowprice_desc = "&sort_type=sort_default";
    /**
     * 价格 － － － 范围
     */
    String lowpANDhighp = "&lowp=500&highp=1000";

    private CatLoadingView mView;
    private ArrayList<String> mSortTypes;
    private ItemDangDang mCurrentBean;
    private String mCurrentName;
    private List<ItemDangDang> mDatas;
    private InnerAdapter mAdapter;
    private InnerHander mHandler;
    private int mCurrentPager = 1;
    private String mCurrentSrot = sort_default;

    private String getCurrentTiaoJian(int pager) {
        String search1 = et1Search.getText().toString();
        String search2 = et2Search.getText().toString();
        if (ProjectUtil.isEmpty(search1)) {
            search1 = "0";
        }
        if (ProjectUtil.isEmpty(search2)) {
            search2 = "9999";
        }


        return mCurrentSrot + url_3c_pager + pager + "&lowp=" + search1 + "&highp=" + search2;
    }

    @Override
    protected void onLCreate() {
        try {
            mCurrentBean = (ItemDangDang) getIntent().getSerializableExtra("");
            if (mCurrentBean == null) {
                mCurrentName = (String) getIntent().getStringExtra("search_type");
                if (mCurrentName == null) {
                    mCurrentName = "避孕套";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search2;
    }

    @Override
    protected void initView() {
        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");
        initActionBar();
        spSearch = (Spinner) findViewById(R.id.sp_search);
        et1Search = (EditText) findViewById(R.id.et1_Search);
        et2Search = (EditText) findViewById(R.id.et2_Search);
        btnSubmit = (Button) findViewById(R.id.btn_Submit);
        lvSearchResult = (XListView) findViewById(R.id.lv_search_result);
        btnSubmit.setOnClickListener(this);
        et1Search.setFocusable(false);
        et2Search.setFocusable(false);
    }

    @Override
    protected void initData() {
        mHandler = new InnerHander();
        mSortTypes = new ArrayList<>();
        mSortTypes.add("默认排序");
        mSortTypes.add("销量排序");
        mSortTypes.add("好评排序");
        mSortTypes.add("最新排序");
        mSortTypes.add("综合排序");
        lvSearchResult.setOnItemClickListener(this);
        lvSearchResult.setXListViewListener(this);
        spSearch.setAdapter(new ArrayAdapter<String>(this, R.layout.item_sp_text, mSortTypes));
        et1Search.setCursorVisible(false);
        et2Search.setCursorVisible(false);
        spSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mCurrentSrot = sort_default;
                        break;
                    case 1:
                        mCurrentSrot = sort_sale_amt_desc;
                        break;
                    case 2:
                        mCurrentSrot = sort_score_desc;
                        break;
                    case 3:
                        mCurrentSrot = sort_last_changed_date_desc;
                        break;
                    case 4:
                        mCurrentSrot = sort_xlowprice_desc;
                        break;
                    default:
                        mCurrentSrot = sort_default;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        new AsyncTask<String, String, List<ItemDangDang>>() {
            @Override
            protected List<ItemDangDang> doInBackground(String... params) {
                return Matherd.getSearchList(mCurrentName, url_3c_pager + mCurrentPager);
            }

            @Override
            protected void onPostExecute(List<ItemDangDang> itemTitlePagers) {
                mDatas = itemTitlePagers;
                mAdapter = new InnerAdapter(Search2Activity.this, mDatas);
                lvSearchResult.setAdapter(mAdapter);
                mView.dismiss();
            }
        }.execute();

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

    private Spinner spSearch;
    private EditText et1Search;
    private EditText et2Search;
    private Button btnSubmit;
    private XListView lvSearchResult;


    @Override
    public void onClick(View v) {
        if (v == btnSubmit) {
            mView.show(getSupportFragmentManager(), "");
            new AsyncTask<String, String, List<ItemDangDang>>() {
                @Override
                protected List<ItemDangDang> doInBackground(String... params) {
                    mCurrentPager = 1;
                    return Matherd.getSearchList(mCurrentName, getCurrentTiaoJian(mCurrentPager));
                }

                @Override
                protected void onPostExecute(List<ItemDangDang> itemTitlePagers) {
                    if (mDatas != null) {
                        mDatas.clear();
                        mDatas.addAll(itemTitlePagers);
                        mAdapter.notifyDataSetChanged();
                    }
                    lvSearchResult.stopLoadMore();
                    mView.dismiss();
                    et2Search.setText("");
                    et1Search.setText("");
                }
            }.execute();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == mDatas.size() - 1 || position < 1) return;
        position -= 1;
        if (position == mDatas.size()) {
            onLoadMore();
            return;
        }
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("itemBean", mDatas.get(position));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0, 1500);
    }

    @Override
    public void onLoadMore() {
        if (mDatas.size() < 60) {
            ProjectUtil.T.show("木有更多啦 ^_^");
            lvSearchResult.stopLoadMore();
            return;
        }
        try {
            new AsyncTask<String, String, List<ItemDangDang>>() {
                @Override
                protected List<ItemDangDang> doInBackground(String... params) {
                    return Matherd.getSearchList(mCurrentName, url_3c_pager + (++mCurrentPager));
                }

                @Override
                protected void onPostExecute(List<ItemDangDang> itemTitlePagers) {
                    if (itemTitlePagers.size() < 50) {
                        ProjectUtil.T.show("木有更多啦 ^_^");
                        lvSearchResult.stopLoadMore();
                        return;
                    }

                    if (mDatas != null) {
                        mDatas.addAll(itemTitlePagers);
                        mAdapter.notifyDataSetChanged();
                    }
                    lvSearchResult.stopLoadMore();
                    mView.dismiss();
                }
            }.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class InnerHander extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Collections.shuffle(mDatas);
                    mAdapter.notifyDataSetChanged();
                    lvSearchResult.stopRefresh();
                    break;

                default:
                    break;
            }
        }
    }


    private class InnerAdapter extends LBaseAdapterMore<ItemDangDang> {


        public InnerAdapter(Context context, List<ItemDangDang> objects) {
            super(context, objects);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public ViewHolder<ItemDangDang> createHolders(int holderType) {
            switch (holderType) {
                case 0:
                    return new InnerHolder_0(R.layout.favourite_item_news, 0);
                case 1:
                    return new InnerHolder_1(R.layout.favourite_item_pics, 1);
            }
            return null;
        }


        @Override
        public void bindHolder(ViewHolder<ItemDangDang> h) {
            switch (h.type) {
                case 0:
                    InnerHolder_0 holder0 = (InnerHolder_0) h;
                    holder0.favorTitle.setText(holder0.data.itemShopStyle);
                    holder0.favorTag.setText(holder0.data.itemShopProce);
                    holder0.favorTime.setText(holder0.data.itemShopMaiJiaHref);
                    holder0.favorBrief.setText(holder0.data.itemShopName);
                    x.image().bind(holder0.favorPic, holder0.data.itemShopImg);
                    break;
                case 1:
                    InnerHolder_1 holder1 = (InnerHolder_1) h;
                    holder1.favorTitle.setText("" + holder1.data.itemShopName);
                    holder1.favorTag.setText("" + holder1.data.itemShopProce);
                    holder1.favorTime.setText("" + holder1.data.itemShopPingLun);
                    x.image().bind(holder1.favorPic1, holder1.data.itemShopImg);
                    x.image().bind(holder1.favorPic2, holder1.data.itemShopImg);
                    x.image().bind(holder1.favorPic3, holder1.data.itemShopImg);

                    break;
                default:
                    break;
            }
        }

        @Override
        public int setHorderType(ItemDangDang bean) {
            return bean.itemShopStyle.length() > 1 ? 0 : 1;
        }

        private class InnerHolder_0 extends ViewHolder<ItemDangDang> {

            public InnerHolder_0(int resID, int type) {
                super(resID, type);
            }

            @Override
            protected ViewHolder<ItemDangDang> findViews(View v) {
                favorDelChose = (TextView) v.findViewById(R.id.favor_del_chose);
                favorTitle = (TextView) v.findViewById(R.id.favor_title);
                favorBrief = (TextView) v.findViewById(R.id.favor_brief);
                favorPic = (ImageView) v.findViewById(R.id.favor_pic);
                favorTag = (TextView) v.findViewById(R.id.favor_tag);
                favorTime = (TextView) v.findViewById(R.id.favor_time);
                return this;
            }

            private TextView favorDelChose;
            private TextView favorTitle;
            private TextView favorBrief;
            private ImageView favorPic;
            private TextView favorTag;
            private TextView favorTime;
        }

        private class InnerHolder_1 extends ViewHolder<ItemDangDang> {

            public InnerHolder_1(int resID, int type) {
                super(resID, type);
            }

            @Override
            protected ViewHolder<ItemDangDang> findViews(View v) {
                favorDelChose = (TextView) v.findViewById(R.id.favor_del_chose);
                favorTitle = (TextView) v.findViewById(R.id.favor_title);
                favorPic1 = (ImageView) v.findViewById(R.id.favor_pic1);
                favorPic2 = (ImageView) v.findViewById(R.id.favor_pic2);
                favorPic3 = (ImageView) v.findViewById(R.id.favor_pic3);
                favorPicCount = (TextView) v.findViewById(R.id.favor_pic_count);
                favorTag = (TextView) v.findViewById(R.id.favor_tag);
                favorTime = (TextView) v.findViewById(R.id.favor_time);
                return this;
            }

            private TextView favorDelChose;
            private TextView favorTitle;
            private ImageView favorPic1;
            private ImageView favorPic2;
            private ImageView favorPic3;
            private TextView favorPicCount;
            private TextView favorTag;
            private TextView favorTime;
        }

    }
}
