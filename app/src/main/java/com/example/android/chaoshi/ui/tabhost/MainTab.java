package com.example.android.chaoshi.ui.tabhost;


import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.CategoryLeftAdapter;
import com.example.android.chaoshi.ui.dialog.CenterBottomDialog;
import com.example.android.chaoshi.ui.fragment.CartFragment;
import com.example.android.chaoshi.ui.fragment.CategoryFragment;
import com.example.android.chaoshi.ui.fragment.CenterFragment;
import com.example.android.chaoshi.ui.fragment.HomeFragment;
import com.example.android.chaoshi.ui.fragment.NewCategoryFragment;

/**
 * Created by Administrator on 2016/7/28.
 */
public enum  MainTab {

    HOME(0, R.string.home, R.drawable.home_selector,
            HomeFragment.class),

    FIND(1, R.string.category, R.drawable.category_selector,
            NewCategoryFragment.class),

    PRICE(2, R.string.pic, R.drawable.optimus__dna_banner,
            CenterBottomDialog.class),

    QUES(3, R.string.gouwuche, R.drawable.gouwuche_selector,
            CartFragment.class),

    ME(4, R.string.self, R.drawable.self_selector,
            CenterFragment.class);



    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;


    MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }


    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
