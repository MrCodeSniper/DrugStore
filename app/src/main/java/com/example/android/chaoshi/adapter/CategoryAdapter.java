package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.SimpleBaseAdapter;
import com.example.android.chaoshi.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 2016/9/6.
 */
public class CategoryAdapter extends SimpleBaseAdapter<String> {

    private Context context;
    List<String> datalist=new ArrayList<>();
    public CategoryAdapter(Context context, List<String> datalist) {
        super(context, datalist);
        this.context=context;
        this.datalist=datalist;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup arg2) {
        ViewHolder viewHolder=null;
        if(convertview==null){
            viewHolder=new ViewHolder();
            convertview=View.inflate(context, R.layout.category_item,null);
            viewHolder.tv_category= (TextView) convertview.findViewById(R.id.tv_category);
            viewHolder.view=convertview.findViewById(R.id.view_line);
            viewHolder.iv= (ImageView) convertview.findViewById(R.id.iv);
            convertview.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) convertview.getTag();
        }
        viewHolder.tv_category.setText(datalist.get(position));
        viewHolder.iv.setImageResource(Constant.SELECTOR[position]);
        return convertview;
    }

    static class ViewHolder{
     TextView tv_category;
      View view;
        ImageView iv;
    }



}
