package com.example.android.chaoshi.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.Base2Fragment;
import com.example.android.chaoshi.bean.user.User;
import com.example.android.chaoshi.util.Tools;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/18.
 */
public class RegisteInputFragment extends Base2Fragment {

    @ViewInject(R.id.apb_register)
    ActionProcessButton apbRegister;
    @ViewInject(R.id.reset_input_name)
    EditText etUsername;
    @ViewInject(R.id.reset_input_pass)
    EditText etPwd;
    @ViewInject(R.id.reset_input_pass2)
    EditText etConfirmPwd;
    @ViewInject(R.id.ll_username)
    LinearLayout llUsername;
    @ViewInject(R.id.ll_pwd)
    LinearLayout llPwd;
    @ViewInject(R.id.ll_comfirm_pwd)
    LinearLayout llComfirmPwd;
    @ViewInject(R.id.ll_agree)
    LinearLayout llAgree;
    @ViewInject(R.id.regist_agree_check)
    CheckBox cbAgree;

    @Override
    protected int bindLayout() {
        return R.layout.user_reset_input;
    }

    @Override
    protected void setInitView(View mRootView) {
        x.view().inject(this, mRootView);
    }

    @Override
    protected void setInitData() {
        apbRegister.setMode(ActionProcessButton.Mode.ENDLESS);
    }

    @Event(R.id.apb_register)
    private void onClick(View view) {
        String username = etUsername.getText().toString();
        String pwd = etPwd.getText().toString();
        String confirmPwd = etConfirmPwd.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Tools.shakeAimation(llUsername);
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Tools.shakeAimation(llPwd);
            return;
        }
        if (!pwd.equals(confirmPwd)) {
            Tools.shakeAimation(llPwd);
            Tools.shakeAimation(llComfirmPwd);
            return;
        }
        if (!cbAgree.isChecked()) {
            Tools.shakeAimation(llAgree);
            return;
        }

        apbRegister.setEnabled(false);
        apbRegister.setProgress(50);

        User user = new User();
        user.setUsername(username);
        user.setPassword(pwd);
        user.signUp(new MySaveListener());
    }

    private class MySaveListener extends SaveListener<User> {
        @Override
        public void done(User user,final BmobException e) {
            apbRegister.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (e == null) {
                        apbRegister.setProgress(100);
                        apbRegister.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().finish();
                            }
                        },1000);
                    } else {
                        apbRegister.setProgress(-1);
                    }
                }
            }, 1300);

        }
    }
}
