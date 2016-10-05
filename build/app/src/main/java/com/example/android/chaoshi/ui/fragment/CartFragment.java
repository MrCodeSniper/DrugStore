package com.example.android.chaoshi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.gifview.library.GifView;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.MyExpandableListAdapter;
import com.example.android.chaoshi.application.ProjectApp;
import com.example.android.chaoshi.bean.shop.Commodity;
import com.example.android.chaoshi.bean.shop.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CartFragment extends Fragment {
    private View view;
    private ArrayList<Store> mStoreList;
    private HashMap<Integer, List<Commodity>> mCartData;
    private ExpandableListView mELvCart;
    private MyExpandableListAdapter mAdapter;
    private CheckBox mCheckAll;
    private TextView mEdit;
    public static boolean isParentSet;
    private boolean isSHow;
    private TextView mTotalPrice;
    private TextView mCommit;
    private RelativeLayout mEmptyCart;

    private GifView gifView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = getLayoutInflater(savedInstanceState).inflate(R.layout.cart_fragment, null);
        initView();
        initDatas();
        setAdapter();
        setListener();
        return view;

    }

    private void setListener() {

        mELvCart.setOnGroupClickListener(new MyGroupClickListener());
        mELvCart.setOnChildClickListener(new MyChildClickListener());
        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isParentSet) {
                    //不做反应
                } else {
                    mAdapter.checkAll(isChecked);
                }
            }
        });
        MyClickListener clickListener = new MyClickListener();
        mEdit.setOnClickListener(clickListener);
        mCommit.setOnClickListener(clickListener);
    }

    private void initView() {
        mELvCart = (ExpandableListView) view.findViewById(R.id.elv_cart);
        mCheckAll = (CheckBox) view.findViewById(R.id.cb_select_all);
        mEdit = (TextView) view.findViewById(R.id.tv_edit_all);
        mCommit = (TextView) view.findViewById(R.id.tv_jiesuan_price);
        mTotalPrice = (TextView) view.findViewById(R.id.tv_totalPrice);
        mEmptyCart = (RelativeLayout) view.findViewById(R.id.rl_cart_is_empty);
        gifView= (GifView) view.findViewById(R.id.cart_is_empty_image);



    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if(gifView.isPlaying()){
            gifView.pause();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden==true){
            if(gifView.isPlaying()){
                gifView.pause();
            }
        }else {
            if(!gifView.isPlaying()){
                gifView.play();
            }

        }
    }


















    private void setAdapter() {
        mAdapter = new MyExpandableListAdapter(this, mStoreList, mCartData);
        mELvCart.setAdapter(mAdapter);
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            mELvCart.expandGroup(i);
        }
    }

    private void initDatas() {
        mStoreList = ProjectApp.getStores();
        mCartData = ProjectApp.getCarts();
    }

    private class MyGroupClickListener implements ExpandableListView.OnGroupClickListener {

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "你单击了："
                    + mAdapter.getGroup(groupPosition), Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class MyChildClickListener implements ExpandableListView.OnChildClickListener {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "你单击了："
                    + mAdapter.getChild(groupPosition, childPosition), Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_edit_all:
                    isSHow = !isSHow;
                    mAdapter.changedAllStatus(isSHow);
                    break;
                case R.id.tv_jiesuan_price:
                    Log.i("LOA", "点击了添加按钮");
                    //跳转界面
                    break;
            }
        }
    }

    //	public void notifydd(){
    //		mAdapter.notifyDataSetChanged();
    //	}
    public void changeCheck(boolean checked) {
        mCheckAll.setChecked(checked);
    }

    public void showEmpty() {
        if (mCartData.size() == 0) {
            mEmptyCart.setVisibility(View.VISIBLE);
            if(!gifView.isPlaying()){
                gifView.play();
            }
        } else {
            mEmptyCart.setVisibility(View.GONE);
            if(gifView.isPlaying()){
                gifView.pause();
            }
        }
    }

    public void omDataChanged() {
        List<Commodity> commitList = mAdapter.getCommitList();
        int count = 0;
        double price = 0;
        for (Commodity commodity : commitList) {
            count++;
            price = price + commodity.getCount() * commodity.getPrice();
        }
        price = (int) price * 100;
        price = Double.valueOf(price) / 100;
        mTotalPrice.setText("" + price);
        mCommit.setText("结算(" + price + ")");
    }

}
