package com.example.android.chaoshi.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * Created by CWQ on 2016/9/14.
 */
public class LikeShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    //    private List<Shop> likeShops;
    private List<ItemDangDang> likeShops;
    private HomeFragment.OnItemClickCallback onItemClickCallback;
    private HomeFragment.OnShoppingCartClickCallBack onShoppingCartClickCallBack;
    private ImageOptions options;

//    public LikeShopAdapter(Context context, List<Shop> likeShops, HomeFragment.OnItemClickCallback onItemClickCallback, HomeFragment.OnShoppingCartClickCallBack onShoppingCartClickCallBack) {
//        this.context = context;
//        this.likeShops = likeShops;
//        this.onItemClickCallback = onItemClickCallback;
//        this.onShoppingCartClickCallBack = onShoppingCartClickCallBack;
//    }


    public LikeShopAdapter(Context context, List<ItemDangDang> likeShops, HomeFragment.OnItemClickCallback onItemClickCallback, HomeFragment.OnShoppingCartClickCallBack onShoppingCartClickCallBack) {
        this.context = context;
        this.likeShops = likeShops;
        this.onItemClickCallback = onItemClickCallback;
        this.onShoppingCartClickCallBack = onShoppingCartClickCallBack;
        options = new ImageOptions.Builder().setSize(UiUtils.dp2px(100), UiUtils.dp2px(100)).build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeShopHolder(UiUtils.inflate(R.layout.recycler_item_like_shop));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemDangDang itemDangDang = likeShops.get(position);
//        ((LikeShopHolder) holder).ivImg.setImageResource(shop.getImgRes());
        x.image().bind(((LikeShopHolder) holder).ivImg, itemDangDang.itemShopImg);
        ((LikeShopHolder) holder).tvName.setText(itemDangDang.itemShopName);
        ((LikeShopHolder) holder).tvPrice.setText("￥" + itemDangDang.originalCost);
        View.OnClickListener onClickListener = new MyOnClickListener(position);
        ((LikeShopHolder) holder).rlShop.setOnClickListener(onClickListener);
        ((LikeShopHolder) holder).ivBuy.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        if (likeShops.size() == 0) {
            return 0;
        }
        return 12;
    }

    class LikeShopHolder extends RecyclerView.ViewHolder {

        @ViewInject(R.id.ll_shop)
        LinearLayout rlShop;
        @ViewInject(R.id.iv_img)
        ImageView ivImg;
        @ViewInject(R.id.tv_name)
        TextView tvName;
        @ViewInject(R.id.tv_price)
        TextView tvPrice;
        @ViewInject(R.id.iv_buy)
        ImageView ivBuy;

        public LikeShopHolder(View itemView) {
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
            switch (v.getId()) {
                case R.id.ll_shop:
                    onItemClickCallback.onItemClick(likeShops.get(position));
                    break;
                case R.id.iv_buy:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定加入购物车吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onShoppingCartClickCallBack.onShoppingCartClick(likeShops.get(position));
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.show();
                    break;
            }
        }
    }
}
