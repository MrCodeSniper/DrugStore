package com.example.android.chaoshi.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.CategoryLeftAdapter;
import com.example.android.chaoshi.adapter.DividerItemDecoration;
import com.example.android.chaoshi.adapter.WareAdapter;
import com.example.android.chaoshi.adapter.WaresAdapter;
import com.example.android.chaoshi.base.BaseAdapter;
import com.example.android.chaoshi.base.BaseFragment;
import com.example.android.chaoshi.bean.CategoryNew;
import com.example.android.chaoshi.entity.Page;
import com.example.android.chaoshi.entity.Wares;
import com.example.android.chaoshi.global.Consts;
import com.example.android.chaoshi.http.OkHttpHelper;
import com.example.android.chaoshi.http.SimpleCallback;
import com.example.android.chaoshi.http.SpotsCallBack;
import com.squareup.okhttp.Response;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static android.R.transition.move;

/**
 * Created by Administrator on 2016/10/2.
 */

public class NewCategoryFragment extends BaseFragment {

    @ViewInject(R.id.recyclerview_category)
    private RecyclerView mRecyclerView;

    private CategoryLeftAdapter mCategoryAdapter;

    private OkHttpHelper mHttpHelper = OkHttpHelper.getInstance();


    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;

    private int currPage = 1;
    private int totalPage = 1;
    private int pageSize = 10;
    private long category_id = 0;

    private boolean move = false;

    @ViewInject(R.id.recyclerview_wares)
    private RecyclerView mRecyclerviewWares;

    private LinearLayoutManager mLinearLayoutManager;


    @ViewInject(R.id.refresh_layout)
    private MaterialRefreshLayout mRefreshLaout;


    private WaresAdapter mWaresAdatper;

//
//    @ViewInject(R.id.header)
//    RecyclerViewHeader header;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_new;
    }

    @Override
    protected void initParams() {
//        header=RecyclerViewHeader.fromXml(getActivity(), R.layout.header);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        requestCategoryData();
        initRefreshLayout();



    }


    private void requestCategoryData() {


        mHttpHelper.get(Consts.API.CATEGORY_LIST, new SpotsCallBack<List<CategoryNew>>(getActivity()) {


            @Override
            public void onSuccess(Response response, List<CategoryNew> categories) {

                showCategoryData(categories);

                if (categories != null && categories.size() > 0)
                    category_id = categories.get(0).getId();
                     requestWares(category_id);//先加载第一页
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }




    private  void initRefreshLayout(){

        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshData();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                if(currPage <=totalPage)
                    loadMoreData();
                else{
//                    Toast.makeText()
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }

    private  void refreshData(){

        currPage =1;

        state=STATE_REFREH;
        requestWares(category_id);


    }

    private void loadMoreData(){

        currPage = ++currPage;
        state = STATE_MORE;
        requestWares(category_id);

    }




    private void showCategoryData(List<CategoryNew> categories) {


        mCategoryAdapter = new CategoryLeftAdapter(getActivity(), categories);

        mCategoryAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                mRecyclerView.smoothScrollToPosition(position);

                CategoryNew category = mCategoryAdapter.getItem(position);

                category_id = category.getId();
                currPage = 1;
                state = STATE_NORMAL;
                requestWares(category_id);
               move(position);

//                RecyclerView.LayoutManager mLayoutManager = mRecyclerView.getLayoutManager();
//                mLayoutManager.scrollToPosition(position);

            }
        });




        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));


    }


    private void smoothMoveToPosition(int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem ){
            mRecyclerView.smoothScrollToPosition(n);
        }else if ( n <= lastItem ){
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }else{
            mRecyclerView.smoothScrollToPosition(n);
            move = true;
        }

    }

    private int mIndex = 0;
    private void move(int n){
        if (n<0 || n>=mCategoryAdapter.getItemCount() ){
            Toast.makeText(getActivity(),"超出范围了",Toast.LENGTH_SHORT).show();
            return;
        }
        mIndex = n;
        mRecyclerView.stopScroll();
        smoothMoveToPosition(n);
    }


    private void requestWares(long categoryId) {

        String url = Consts.API.WARES_LIST + "?categoryId=" + categoryId + "&curPage=" + currPage + "&pageSize=" + pageSize;

        mHttpHelper.get(url, new SimpleCallback<Page<Wares>>(getActivity()) {

            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {

                currPage = waresPage.getCurrentPage();
                totalPage = waresPage.getTotalPage();

                showWaresData(waresPage.getList());
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    private void showWaresData(List<Wares> wares) {

        switch (state) {

            case STATE_NORMAL:

                if (mWaresAdatper == null) {
                    mWaresAdatper = new WaresAdapter(getActivity(), wares);
                    mWaresAdatper.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Wares wares = mWaresAdatper.getItem(position);
                            Intent intent = new Intent(getActivity(), WareDetailActivity.class);
                            intent.putExtra(Consts.WARE, wares);
                            startActivity(intent);

                        }
                    });


                    mRecyclerviewWares.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//                    header.attachTo(mRecyclerviewWares);
                    mRecyclerviewWares.setAdapter(mWaresAdatper);
                    mRecyclerviewWares.setItemAnimator(new DefaultItemAnimator());
//                    mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));

                } else {
                    mWaresAdatper.clear();
                    mWaresAdatper.addData(wares);
                }




                break;

            case STATE_REFREH:
                mWaresAdatper.clear();
                mWaresAdatper.addData(wares);

                mRecyclerviewWares.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;

            case STATE_MORE:
                mWaresAdatper.addData(mWaresAdatper.getDatas().size(), wares);
                mRecyclerviewWares.scrollToPosition(mWaresAdatper.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;


        }

    }
}
