package com.example.android.chaoshi.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.CategoryLeftAdapter;
import com.example.android.chaoshi.adapter.DividerItemDecoration;
import com.example.android.chaoshi.base.BaseAdapter;
import com.example.android.chaoshi.base.BaseFragment;
import com.example.android.chaoshi.bean.CategoryNew;
import com.example.android.chaoshi.global.Consts;
import com.example.android.chaoshi.http.OkHttpHelper;
import com.example.android.chaoshi.http.SpotsCallBack;
import com.squareup.okhttp.Response;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */

public class NewCategoryFragment extends BaseFragment {

    @ViewInject(R.id.recyclerview_category)
    private RecyclerView mRecyclerView;

    private CategoryLeftAdapter mCategoryAdapter;

    private OkHttpHelper mHttpHelper = OkHttpHelper.getInstance();



    private  static final int STATE_NORMAL=0;
    private  static final int STATE_REFREH=1;
    private  static final int STATE_MORE=2;

    private int state=STATE_NORMAL;

    private int currPage=1;
    private int totalPage=1;
    private int pageSize=10;
    private long category_id=0;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_new;
    }

    @Override
    protected void initParams() {
        requestCategoryData();

    }



    private  void requestCategoryData(){



        mHttpHelper.get(Consts.API.CATEGORY_LIST, new SpotsCallBack<List<CategoryNew>>(getActivity()) {


            @Override
            public void onSuccess(Response response, List<CategoryNew> categories) {

                showCategoryData(categories);

                if(categories !=null && categories.size()>0)
                    category_id = categories.get(0).getId();
//                requestWares(category_id);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    private  void showCategoryData(List<CategoryNew> categories){


        mCategoryAdapter = new CategoryLeftAdapter(getActivity(),categories);

        mCategoryAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                CategoryNew category = mCategoryAdapter.getItem(position);

                category_id = category.getId();
                currPage=1;
                state=STATE_NORMAL;

//                requestWares(category_id);


            }
        });

        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));


    }

}
