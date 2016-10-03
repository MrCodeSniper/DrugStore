package com.example.android.chaoshi.ui.fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.Base2Fragment;
import com.example.android.chaoshi.bean.user.User;
import com.example.android.chaoshi.util.StringUtils;
import com.example.android.chaoshi.util.ToastUtils;
import com.example.android.chaoshi.util.Tools;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/18.
 */
public class RegistePhoneFragment extends Base2Fragment {

    @ViewInject(R.id.regist_phone)
    EditText etPhone;
    @ViewInject(R.id.regist_code)
    EditText etCode;
    @ViewInject(R.id.btn_code)
    Button btnCode;
    @ViewInject(R.id.apb_register)
    ActionProcessButton apbRegister;
    @ViewInject(R.id.reset_input_pass)
    EditText etPwd;
    @ViewInject(R.id.ll_pwd)
    LinearLayout llPwd;
    @ViewInject(R.id.ll_code)
    LinearLayout llCode;

    private TimeCount timeCount;

    @Override
    protected int bindLayout() {
        return R.layout.user_register_phone;
    }

    @Override
    protected void setInitView(View mRootView) {
        x.view().inject(this, mRootView);
    }

    @Override
    protected void setInitData() {
        timeCount = new TimeCount(60000, 1000);
    }

    @Event(value = {R.id.btn_code, R.id.apb_register})
    private void onClick(View view) {
        String phone = etPhone.getText().toString();
        switch (view.getId()) {
            case R.id.btn_code:
                String mobliePhoneRegex = "^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
                if (StringUtils.isEmpty(phone)) {
                    Tools.shakeAimation(etPhone);
                    return;
                } else if (!phone.matches(mobliePhoneRegex)) {
                    Tools.shakeAimation(etPhone);
                    ToastUtils.makeText(getActivity(), "手机号码格式不正确");
                    return;
                }

                BmobSMS.requestSMSCode(phone, "模板名称", new MyQueryListener());
                timeCount.start();
                break;
            case R.id.apb_register:
                String code = etCode.getText().toString();
                String pwd = etPwd.getText().toString();
                if (StringUtils.isEmpty(code)) {
                    Tools.shakeAimation(llCode);
                    return;
                }
                if (StringUtils.isEmpty(pwd)) {
                    Tools.shakeAimation(llPwd);
                    return;
                }

                User user = new User();
                user.setMobilePhoneNumber(phone);
                user.setPassword(pwd);
                user.signOrLogin(code, new MySaveListener());

                apbRegister.setProgress(50);
                break;
        }
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
                        }, 1000);
                    } else {
                        apbRegister.setProgress(-1);
                    }
                }
            },1300);
        }
    }

    private class MyQueryListener extends QueryListener<Integer> {

        @Override
        public void done(Integer integer, BmobException e) {
            if (e == null) {
            } else {
                e.printStackTrace();
            }
        }
    }

    private class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnCode.setText(millisUntilFinished / 1000 + "秒");
            btnCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            btnCode.setText("重新证码");
            btnCode.setClickable(true);

        }
    }
}
