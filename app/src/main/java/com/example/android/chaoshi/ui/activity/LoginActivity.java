package com.example.android.chaoshi.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.application.MyApplication;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.bean.user.User;
import com.example.android.chaoshi.util.Tools;

import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/18.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.login_username)
    EditText etUsername;
    @ViewInject(R.id.login_password)
    EditText etPwd;
    @ViewInject(R.id.apb_login)
    ActionProcessButton apbLogin;
    @ViewInject(R.id.apb_logout)
    ActionProcessButton apbLogout;
    private TextView mTvRegister;
    private String username;
    private String pwd;

    @Override
    protected void onLCreate() {
        initActionBar();

    }

    @Override
    public int getLayoutID() {
        return R.layout.user_login_plats;
    }

    @Override
    protected void initView() {
        mTvRegister = (TextView) findViewById(R.id.login_register_txt);
    }

    @Override
    protected void initData() {
        mTvRegister.setOnClickListener(this);
        apbLogin.setOnClickListener(this);
        apbLogout.setOnClickListener(this);

        if (MyApplication.curState == MyApplication.STATE_LOGIN) {
            etUsername.setEnabled(false);
            etPwd.setEnabled(false);
            apbLogin.setVisibility(View.GONE);
            apbLogout.setVisibility(View.VISIBLE);
            etUsername.setText(MyApplication.username);
            etPwd.setText(MyApplication.pwd);
            etUsername.setTextColor(Color.parseColor("#999999"));
            etPwd.setTextColor(Color.parseColor("#999999"));
        } else {
            etUsername.setEnabled(true);
            etPwd.setEnabled(true);
            apbLogin.setVisibility(View.VISIBLE);
            apbLogout.setVisibility(View.GONE);
            etUsername.setTextColor(Color.parseColor("#666666"));
            etPwd.setTextColor(Color.parseColor("#666666"));
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("返回");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == mTvRegister) {
            startActivity(new Intent(this, RegistActivity.class));
        }
        switch (v.getId()) {
            case R.id.apb_login:
                username = etUsername.getText().toString();
                pwd = etPwd.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Tools.shakeAimation(etUsername);
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Tools.shakeAimation(etPwd);
                    return;
                }
                User user = new User();
                user.setUsername(username);
                user.setPassword(pwd);
                user.login(new MySaveListener());
                apbLogin.setProgress(50);
                break;
            case R.id.apb_logout:
                etUsername.setEnabled(true);
                etPwd.setEnabled(true);
                apbLogin.setVisibility(View.VISIBLE);
                apbLogout.setVisibility(View.GONE);
                etUsername.setText("");
                etPwd.setText("");
                etUsername.setFocusable(true);
                MyApplication.username = "";
                MyApplication.pwd = "";
                etUsername.setTextColor(Color.parseColor("#666666"));
                etPwd.setTextColor(Color.parseColor("#666666"));
                break;
        }
    }

    private class MySaveListener extends SaveListener<User> {

        @Override
        public void done(User user, final BmobException e) {
            apbLogin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (e == null) {
                        apbLogin.setProgress(100);
                        MyApplication.curState = MyApplication.STATE_LOGIN;
                        MyApplication.username = username;
                        MyApplication.pwd = pwd;
                        etUsername.setEnabled(false);
                        etPwd.setEnabled(false);
                        etUsername.setTextColor(Color.parseColor("#999999"));
                        etPwd.setTextColor(Color.parseColor("#999999"));
                        apbLogin.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    } else {
                        apbLogin.setProgress(-1);
                    }
                }
            }, 1500);
        }
    }
}
