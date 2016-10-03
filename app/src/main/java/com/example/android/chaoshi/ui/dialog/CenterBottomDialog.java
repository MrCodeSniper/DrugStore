package com.example.android.chaoshi.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.base.LBaseAdapter;
import com.example.android.chaoshi.bean.shop.MoreItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class CenterBottomDialog extends AlertDialog implements View.OnClickListener{


    private ArrayList<MoreItem> mItems;

    public CenterBottomDialog(Context context) {
        super(context,R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddalert_menu_grid_layout);
        findViews();
        initDatas();
    }

    private void initDatas() {
        mItems = new ArrayList<>();
        mItems.add(new MoreItem("发现",R.drawable.share_icon_email));
        mItems.add(new MoreItem("附近",R.drawable.share_icon_message));
        mItems.add(new MoreItem("评论",R.drawable.share_icon_qq));
        mItems.add(new MoreItem("二手",R.drawable.share_icon_qzone));
        mItems.add(new MoreItem("没想好",R.drawable.share_icon_sina));
        mItems.add(new MoreItem("慢慢找",R.drawable.share_icon_tencent));
        mItems.add(new MoreItem("不着急",R.drawable.share_icon_wechat));
        mItems.add(new MoreItem("又困了",R.drawable.share_icon_wechatmoments));
        ddalertMenuTitle.setText("更多");
        ddalertMenuGrid.setAdapter(new InnerAdapter(getContext(),R.layout.ddalert_menu_grid_item,mItems));
    }

    private TextView ddalertMenuTitle;
    private GridView ddalertMenuGrid;
    private Button ddalertMenuCancelBtn;

    private void findViews() {
        ddalertMenuTitle = (TextView)findViewById( R.id.ddalert_menu_title );
        ddalertMenuGrid = (GridView)findViewById( R.id.ddalert_menu_grid );
        ddalertMenuCancelBtn = (Button)findViewById( R.id.ddalert_menu_cancel_btn );
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.AnimBottom);  //添加动画
        ddalertMenuCancelBtn.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == ddalertMenuCancelBtn ) {
            dismiss();
        }
    }
    private class InnerAdapter extends LBaseAdapter<MoreItem>{

        public InnerAdapter(Context context, int viewid, List<MoreItem> objects) {
            super(context, viewid, objects);
        }

        @Override
        protected ViewHolder<MoreItem> createHolder(View v) {
            return new InnerHolder().findViews(v);
        }

        @Override
        protected void bindHolder(ViewHolder<MoreItem> h) {
            InnerHolder holder = (InnerHolder) h;
            holder.ddalertMenuGridItemIcon.setImageResource(holder.data.resId);
            holder.ddalertMenuGridItemTv.setText(holder.data.itemName);
        }
        private class InnerHolder extends ViewHolder<MoreItem>{
            private ImageView ddalertMenuGridItemIcon;
            private TextView ddalertMenuGridItemTv;

            private InnerHolder findViews(View v) {
                ddalertMenuGridItemIcon = (ImageView)v.findViewById( R.id.ddalert_menu_grid_item_icon );
                ddalertMenuGridItemTv = (TextView)v.findViewById( R.id.ddalert_menu_grid_item_tv );
                return this;
            }

        }
    }
}
