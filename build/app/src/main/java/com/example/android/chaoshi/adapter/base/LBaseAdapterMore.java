package com.example.android.chaoshi.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class LBaseAdapterMore<T> extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<T> mDataObjects;

    public abstract class ViewHolder<T> {
        public T data;
        public int resID;
        public int type;

        public ViewHolder(int resID, int type) {
            this.resID = resID;
            this.type = type;
        }

        private View getHolderView() {
            return mInflater.inflate(resID, null);
        }

        protected abstract ViewHolder<T> findViews(View v);
    }

    public LBaseAdapterMore(Context context, List<T> objects) {
        mInflater = LayoutInflater.from(context);
        mDataObjects = objects;
        if (objects == null) {
            mDataObjects = new ArrayList<T>();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return setHorderType(getItem(position));
    }


    public final int getCount() {
        return mDataObjects.size();
    }

    public final T getItem(int position) {
        return mDataObjects.get(position);
    }

    public final long getItemId(int position) {
        return position;
    }


    @SuppressWarnings("unchecked")
    public final View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);


        ViewHolder<T> holder = null;
        if (convertView == null) {
            holder = createHolders(type);
            convertView = holder.getHolderView();
            holder.findViews(convertView);
            convertView.setTag(holder);
        } else {
            ViewHolder<T> convertHolder = (ViewHolder<T>) convertView.getTag();
            if (convertHolder.type == type) {
                holder = convertHolder;
            } else {
                holder = createHolders(type);
                convertView = holder.getHolderView();
                holder.findViews(convertView);
                convertView.setTag(holder);
            }
        }
        holder.data = getItem(position);
        bindHolder(holder);
        return convertView;
    }


    @Override
    public abstract int getViewTypeCount();

    /**
     * 这个实体类应该加载的ViewHolder
     */
    public abstract ViewHolder<T> createHolders(int holderType);

    /**
     * 这个实体类加载布局后的VIewHolder
     */
    public abstract void bindHolder(ViewHolder<T> h);

    /**
     * 这个实体类应该加载哪个布局？返回第几个布局
     */
    public abstract int setHorderType(T bean);

}
