package com.example.android.chaoshi.ui.fragment;

import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.Base2Fragment;
import com.example.android.chaoshi.util.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/9/18.
 */
public class RegisteMailFragment extends Base2Fragment {

    @ViewInject(R.id.apb_register)
    ActionProcessButton apbRegister;

    @Override
    protected int bindLayout() {
        return R.layout.user_register_mail;
    }

    @Override
    protected void setInitView(View mRootView) {
        x.view().inject(this, mRootView);
    }

    @Override
    protected void setInitData() {

    }

    @Event(R.id.apb_register)
    private void onClick(View view) {
        String email = "conghuahuadan@163.com";
        BmobUser.requestEmailVerify(email, new MyUpdateListener());
    }

    private class MyUpdateListener extends UpdateListener {
        @Override
        public void done(BmobException e) {
            if (e==null) {
                ToastUtils.makeText(getActivity(),"成功");
            }else {
                ToastUtils.makeText(getActivity(),"失败");
                e.printStackTrace();
            }
        }
    }
}
