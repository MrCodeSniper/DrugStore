package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.RightCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 2016/9/6.
 */
public class CategorySecondAdapter extends BaseAdapter {

    private String[]  cate;

    private Context context;

    List<RightCategory> datalist=new ArrayList<>();

    List<RightCategory> datas=new ArrayList<>();
    MyGridAdapter mgridAdapter;


    public CategorySecondAdapter(Context context, List<RightCategory> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

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
    public View getView(int position, View convertview, ViewGroup arg2) {



        datas.clear();
        ViewHolder viewHolder=null;
        if(convertview==null){

            viewHolder=new ViewHolder();
            convertview=View.inflate(context, R.layout.category_item_second,null);
            viewHolder.tv_title= (TextView) convertview.findViewById(R.id.tv_title);
            viewHolder.gv= (GridView) convertview.findViewById(R.id.gv);
            convertview.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) convertview.getTag();
        }



         viewHolder.tv_title.setText(cate[position]);

        for(int i=0;i<datalist.size();i++){
        if(datalist.get(i).tag.equals(cate[position])){
            datas.add(datalist.get(i));
        }
        }

            mgridAdapter=new MyGridAdapter(datas,context);
            viewHolder.gv.setAdapter(mgridAdapter);


        return convertview;
    }

    static class ViewHolder{
     TextView tv_title;
     GridView gv;
    }







}
