package com.example.android.chaoshi.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.bean.shop.ItemDangDang;
import com.example.android.chaoshi.ui.fragment.HomeFragment;
import com.example.android.chaoshi.util.UiUtils;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by CWQ on 2016/9/12.
 */
public class GroupBuyingShopAdapter extends RecyclerView.Adapter<GroupBuyingShopAdapter.GroupBuyingShopHolder> {

    //    private List<Shop> groupBuyingShops;
    private List<ItemDangDang> groupBuyingShops;
    private HomeFragment.OnItemClickCallback onItemClickCallback;
    private ImageOptions options;

//    public GroupBuyingShopAdapter(List<Shop> groupBuyingShops, HomeFragment.OnItemClickCallback onItemClickCallback) {
//        this.groupBuyingShops = groupBuyingShops;
//        this.onItemClickCallback = onItemClickCallback;
//    }


    public GroupBuyingShopAdapter(List<ItemDangDang> groupBuyingShops, HomeFragment.OnItemClickCallback onItemClickCallback) {
        this.groupBuyingShops = groupBuyingShops;
        this.onItemClickCallback = onItemClickCallback;
        options = new ImageOptions.Builder().setSize(UiUtils.dp2px(100), UiUtils.dp2px(100)).build();
    }

    @Override
    public GroupBuyingShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupBuyingShopHolder(UiUtils.inflate(R.layout.recycler_item_group_buying_shop));
    }

    @Override
    public void onBindViewHolder(GroupBuyingShopHolder holder, int position) {

        ItemDangDang itemDangDang = groupBuyingShops.get(position);
        x.image().bind(holder.ivImg, itemDangDang.itemShopImg, options);
        holder.tvName.setText(itemDangDang.itemShopName);
        holder.tvPrice.setText("￥" + itemDangDang.originalCost);
        holder.rlShop.setOnClickListener(new MyOnClickListener(position));

    }

    @Override
    public int getItemCount() {
        if (groupBuyingShops.size() == 0) {
            return 0;
        }
        return 12;
    }

    class GroupBuyingShopHolder extends RecyclerView.ViewHolder {

        @ViewInject(R.id.rl_shop)
        RelativeLayout rlShop;
        @ViewInject(R.id.iv_img)
        ImageView ivImg;
        @ViewInject(R.id.tv_name)
        TextView tvName;
        @ViewInject(R.id.tv_price)
        TextView tvPrice;

        public GroupBuyingShopHolder(View itemView) {
            super(itemView);

            x.view().inject(this, itemView);

        }
    }

    private class MyOnClickListener implements View.OnClickListener {

        private int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            onItemClickCallback.onItemClick(groupBuyingShops.get(position));
        }
    }
}
