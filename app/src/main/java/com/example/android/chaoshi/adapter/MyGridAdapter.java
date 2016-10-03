package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.RightCategory;
import com.example.android.chaoshi.ui.activity.Search2Activity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 2016/9/9.
 */
public class MyGridAdapter extends BaseAdapter {


    List<RightCategory> datas = new ArrayList<>();
    Context context;

    public MyGridAdapter(List<RightCategory> datas, Context context) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.grid_item, null);

        final TextView tv = (TextView) view.findViewById(R.id.tv_text);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);

        tv.setText(datas.get(position).title);
        ImageLoader.getInstance().displayImage(datas.get(position).image.getFileUrl(), iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Search2Activity.class);
                intent.putExtra("search_type", tv.getText().toString());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
