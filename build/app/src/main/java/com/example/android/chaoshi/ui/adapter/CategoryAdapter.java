package com.example.android.chaoshi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.bean.shop.Category;
import com.example.android.chaoshi.ui.fragment.HomeFragment;
import com.example.android.chaoshi.util.UiUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by CWQ on 2016/9/8.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private Context context;
    private List<Category> categories;
    private HomeFragment.OnItemClickCallback onItemClickCallback;
    private HomeFragment.OnCategoryClickCallback onCategoryClickCallback;

//    public CategoryAdapter(Context context, List<Category> categories, HomeFragment.OnItemClickCallback onItemClickCallback) {
//        this.context = context;
//        this.categories = categories;
//        this.onItemClickCallback = onItemClickCallback;
//    }


    public CategoryAdapter(Context context, List<Category> categories, HomeFragment.OnCategoryClickCallback onCategoryClickCallback) {
        this.context = context;
        this.categories = categories;
        this.onCategoryClickCallback = onCategoryClickCallback;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = UiUtils.inflate(R.layout.recycler_item_category);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        Category category = categories.get(position);
        holder.ivIcon.setImageResource(category.getIcon());
        holder.tvTitle.setText(category.getTitle());
        holder.llCategory.setOnClickListener(new InnerOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        @ViewInject(R.id.ll_category)
        LinearLayout llCategory;
        @ViewInject(R.id.iv_icon)
        ImageView ivIcon;
        @ViewInject(R.id.tv_title)
        TextView tvTitle;

        public CategoryHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);
        }
    }

    private class InnerOnClickListener implements View.OnClickListener {

        private int position;

        public InnerOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            onCategoryClickCallback.onCategroyClick(categories.get(position).getUrl());
        }
    }
}
