package com.example.android.chaoshi.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.application.ProjectApp;

import org.xutils.x;

/**
 * Created by Android on 2016/9/6.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static BaseActivity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onLCreate();
        setContentView(getLayoutID());
        ProjectApp.getmActivitys().add(this);
        x.view().inject(this);
        initView();
        initData();
    }

    /**
     * 等同于onCreate，为了自动生成方便点儿  ^_^......
     * 可以空实现,可以初始化Toobar什么滴
     */
    protected abstract void onLCreate();

    public abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 显示Toast
     */
    public void showToast(String content) {
        ProjectApp.getToast(content).show();
    }

    /**
     * 直接返回当前activity对象
     *
     * @return
     */
    public static BaseActivity getActivity() {
        return mActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProjectApp.getmActivitys().remove(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
