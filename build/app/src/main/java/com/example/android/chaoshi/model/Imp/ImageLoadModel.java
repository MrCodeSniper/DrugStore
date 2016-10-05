package com.example.android.chaoshi.model.Imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.android.chaoshi.R;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by Android on 2016/9/19.
 */
public class ImageLoadModel {
    private RequestQueue requestQueue;
    private Map<String,SoftReference<Bitmap>> cache = new HashMap<String,SoftReference<Bitmap>>();

    public ImageLoadModel(Context context) {
       requestQueue = Volley.newRequestQueue(context);
    }

    public void imageLoad(String url, ImageView ivPhoto,int width,int height){
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                SoftReference<Bitmap> soft = cache.get(s);
                if(soft!=null){
                    Bitmap b = soft.get();
                    if(b!=null){
                        return b;
                    }
                }
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,new SoftReference<Bitmap>(bitmap));
            }
        });

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(ivPhoto, R.drawable.ic_category_mark, R.drawable.ic_category_mark);

        if(width == 0|| height==0){
            imageLoader.get(url,listener);
        }else{
            imageLoader.get(url,listener,90,90);
        }

    }
}
