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
 * Created by CWQ on 2016/9/11.
 */
public class TodayPriceShopAdapter extends RecyclerView.Adapter<TodayPriceShopAdapter.TodayPriceShopHolder> {

//    private List<Shop> todayPriceShops;
    private HomeFragment.OnItemClickCallback onItemClickCallback;
    private List<ItemDangDang> todayPriceShops;
    private ImageOptions options;

//    public TodayPriceShopAdapter(List<Shop> todayPriceShops, HomeFragment.OnItemClickCallback onItemClickCallback) {
//        this.todayPriceShops = todayPriceShops;
//        this.onItemClickCallback = onItemClickCallback;
//    }


    public TodayPriceShopAdapter(List<ItemDangDang> todayPriceShops, HomeFragment.OnItemClickCallback onItemClickCallback) {
        this.todayPriceShops = todayPriceShops;
        this.onItemClickCallback = onItemClickCallback;
        options = new ImageOptions.Builder().setSize(UiUtils.dp2px(100), UiUtils.dp2px(100)).build();
    }

    @Override
    public TodayPriceShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodayPriceShopHolder(UiUtils.inflate(R.layout.recycler_item_today_price_shop));
    }

    @Override
    public void onBindViewHolder(TodayPriceShopHolder holder, int position) {
//        Shop shop = todayPriceShops.get(position);
//        holder.ivImg.setImageResource(shop.getImgRes());
//        holder.tvName.setText(shop.getName());
//        holder.tvPrice.setText(shop.getPriceStr());
//        holder.rlShop.setOnClickListener(new MyOnClickListener(position));
        ItemDangDang itemDangDang = todayPriceShops.get(position);
//        holder.ivImg.setImageResource(itemDangDang.getImgRes());

        x.image().bind(holder.ivImg, itemDangDang.itemShopImg, options);
        holder.tvName.setText(itemDangDang.itemShopName);
        holder.tvPrice.setText("￥" + itemDangDang.discountPrice);
        holder.tvOriginalCost.setText("￥" + itemDangDang.originalCost);
        holder.rlShop.setOnClickListener(new MyOnClickListener(position));

    }

    @Override
    public int getItemCount() {
        if (todayPriceShops.size()==0) {
           return 0;
        }
        return 12;
    }

    class TodayPriceShopHolder extends RecyclerView.ViewHolder {

        @ViewInject(R.id.rl_shop)
        RelativeLayout rlShop;
        @ViewInject(R.id.iv_img)
        ImageView ivImg;
        @ViewInject(R.id.tv_name)
        TextView tvName;
        @ViewInject(R.id.tv_price)
        TextView tvPrice;
        @ViewInject(R.id.tv_original_cost)
        TextView tvOriginalCost;

        public TodayPriceShopHolder(View itemView) {
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
            onItemClickCallback.onItemClick(todayPriceShops.get(position));
        }
    }
}
