package com.example.android.chaoshi.ui.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.CategoryAdapter;
import com.example.android.chaoshi.adapter.CategorySecondAdapter;
import com.example.android.chaoshi.adapter.MyGridAdapter;
import com.example.android.chaoshi.base.BaseFragment;
import com.example.android.chaoshi.base.ProjectUtil;
import com.example.android.chaoshi.constant.Constant;
import com.example.android.chaoshi.entity.RightCategory;
import com.example.android.chaoshi.model.Imp.CategoryImp;
import com.example.android.chaoshi.model.callback.BmobCallback;
import com.example.android.chaoshi.ui.view.InGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.roger.catloadinglibrary.CatLoadingView;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by Android on 2016/9/6.
 */
public class CategoryFragment extends BaseFragment {

    @ViewInject(R.id.lv_categroy)
    private ListView lv_category;
    @ViewInject(R.id.lv_categroy_second)
    private ListView lv_category_second;
    @ViewInject(R.id.iv_no)
    private ImageView iv_nothing;

    private List<String> list = new ArrayList<>();
    private CategoryImp categoryImp;
    private String[] cate = {"休闲零食", "饼干/糕点"};
    private String[] cate1 = {"进口牛奶/奶粉", "进口水/饮料/茶"};


    private List<RightCategory> list0 = new ArrayList<>();
    private List<RightCategory> list1 = new ArrayList<>();
    private List<RightCategory> list2 = new ArrayList<>();
    private CatLoadingView mView;

    private CarVideoGridViewAdapter adapter;
    private CategorySecondAdapter CategorySecondAdapter;
    private ImageView iv_title;
    private View view;
    private boolean isadd = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initParams() {


        view = View.inflate(getActivity(), R.layout.header_view, null);
        iv_title = (ImageView) view.findViewById(R.id.iv_title);


        categoryImp = new CategoryImp();
        mView = new CatLoadingView();
        for (int i = 0; i < Constant.CATEGORY.length; i++) {
            list.add(Constant.CATEGORY[i]);
        }
        lv_category.setAdapter(new CategoryAdapter(getActivity(), this.list));
        lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                categoryImp.queryFirstData(new BmobCallback() {
                    @Override
                    public void LoadComplete(List list) {
                        switch (position) {
                            case 0:
                                divideDatas(list, cate);
                                break;
                            case 1:
                                divideDatas(list, cate1);
                                break;
                        }
                        setAdapter();


                        iv_nothing.setVisibility(View.GONE);
                    }

                    @Override
                    public void LoadError(BmobException e) {
                        Log.e("tazzz", e.getMessage());
                        mView.dismiss();
                        iv_nothing.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void LoadStart(BmobQuery query) {
                        mView.show(getFragmentManager(), "");
                        query.addWhereEqualTo("position", position);
                        Log.e("tazzz", query.hasCachedResult(RightCategory.class) + "");
                        if (query.hasCachedResult(RightCategory.class)) {
                            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ONLY);
                        } else {
                            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ONLY);
                        }


//                        if(NetWorkUtils.isNetworkAvailable(getActivity())){
//                            Log.e("tazzz","可用网络");
//                            //缓存
//
//                        }else {
//                            Log.e("tazzz","不可用的网络");
//                            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ONLY);
//                        }
//                        if(!query.hasCachedResult(RightCategory.class)){
//                            lv_category_second.setVisibility(View.GONE);
//
//                        }
                    }
                });
            }
        });
        lv_category.setItemChecked(0, true);
        lv_category.performItemClick(lv_category.getChildAt(0), 0, lv_category.getItemIdAtPosition(0));
        lv_category_second.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectUtil.T.show("点了标题 - - - > " + position);
            }
        });
    }

    private void setAdapter() {
        if (!isadd) {
            lv_category_second.addHeaderView(view);
            isadd = true;
        }
        if (adapter == null) {
            adapter = new CarVideoGridViewAdapter();
            lv_category_second.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        mView.dismiss();
    }

    private void divideDatas(List<RightCategory> list, String[] cates) {
        list0.clear();
        list1.clear();
        //将取到的数据分开
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).tag.equals(cates[0])) {
                list0.add(list.get(i));
            } else if (list.get(i).tag.equals(cates[1])) {
                list1.add(list.get(i));
            } else if (list.get(i).tag.equals("标题图片")) {
                ImageLoader.getInstance().displayImage(list.get(i).image.getFileUrl(), iv_title);
            }
        }
    }


    class CarVideoGridViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return cate.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.listview_gridview, null);
            }
            final ViewHolder viewHolder = ViewHolder.getHolder(convertView);
            viewHolder.tv_title.setText(cate[position]);
            if (position == 0) {
                viewHolder.gv.setAdapter(new MyGridAdapter(list0, getActivity()));
            } else if (position == 1) {
                viewHolder.gv.setAdapter(new MyGridAdapter(list1, getActivity()));
               
            }
           
            return convertView;
        }

    }


    static class ViewHolder {
        TextView tv_title;
        InGridView gv;

        public ViewHolder(View convertview) {
            tv_title = (TextView) convertview.findViewById(R.id.tv_lv_title);
            gv = (InGridView) convertview.findViewById(R.id.gv);
        }

        public static ViewHolder getHolder(View convertview) {
            ViewHolder viewHolder = (ViewHolder) convertview.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder(convertview);
                convertview.setTag(viewHolder);
            }
            return viewHolder;
        }
    }


}
