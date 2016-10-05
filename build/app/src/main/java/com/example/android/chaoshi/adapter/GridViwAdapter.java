package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.bean.shop.Item3CShuMa;
import com.example.android.chaoshi.model.Imp.ImageLoadModel;

import java.util.List;


/**
 * Created by Android on 2016/9/13.
 */
public class GridViwAdapter extends BaseAdapter {
    private Context context;
    private List<Item3CShuMa> list ;
    private ImageLoadModel model;
//    private RequestQueue requestQueue;


    public GridViwAdapter(Context context, List<Item3CShuMa> list) {
        this.context = context;
        this.list = list;
        model = new ImageLoadModel(context);
//        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.gridview_item,null);
            holder = new ViewHolder();
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.imgeview);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item3CShuMa shuMa = list.get(position);
        holder.tvTitle.setText(shuMa.getTitle()+"");
        holder.tvPrice.setText(shuMa.getNum()+"");
        model.imageLoad(shuMa.getImg(),holder.ivPhoto,90,90);

//        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
//            @Override
//            public Bitmap getBitmap(String s) {
//                SoftReference<Bitmap> soft = cache.get(s);
//                if(soft!=null){
//                    Bitmap b = soft.get();
//                    if(b!=null){
//                        return b;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public void putBitmap(String s, Bitmap bitmap) {
//                cache.put(s,new SoftReference<Bitmap>(bitmap));
//            }
//        });
//
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.ivPhoto,R.drawable.ic_category_mark, R.drawable.ic_category_mark);
//
//        imageLoader.get(shuMa.getImg(),listener,90,90);

        return convertView;
    }

//    private Map<String,SoftReference<Bitmap>> cache = new HashMap<String,SoftReference<Bitmap>>();

    class ViewHolder {
        TextView tvTitle;
        TextView tvPrice;
        ImageView ivPhoto;
    }
}
