package com.example.android.chaoshi.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by Android on 2016/9/7.
 */
public class SearchActivity extends BaseActivity {


    @ViewInject(R.id.recycleview)
    private RecyclerView recycleview;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    private String search;

    @ViewInject(R.id.et_search)
    private EditText et_search;


    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        search = getIntent().getStringExtra("search");
        et_search.setText(search);
        et_search.setCursorVisible(false);


        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setCursorVisible(true);
            }
        });

    }

    @Override
    protected void initData() {

    }



}
