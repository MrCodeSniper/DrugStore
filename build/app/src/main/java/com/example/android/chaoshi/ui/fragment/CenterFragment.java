package com.example.android.chaoshi.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.example.android.chaoshi.R;
import com.example.android.chaoshi.base.Base2Fragment;
import com.example.android.chaoshi.base.ProjectUtil;
import com.example.android.chaoshi.ui.activity.CommitOrderActivity;
import com.example.android.chaoshi.ui.activity.LoginActivity;
import com.example.android.chaoshi.ui.activity.ManageAddressActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/9/18.
 */
public class CenterFragment extends Base2Fragment implements View.OnClickListener {

    private BmobUser mCurrentUser;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_center;
    }

    @Override
    protected void setInitView(View mRootView) {
        homeScrollView = (PullToZoomScrollViewEx) mRootView.findViewById(R.id.home_scroll_view);

        View userHeardView = View.inflate(getActivity(), R.layout.usercenter_home_header, null);
        userInfoLayout = (LinearLayout) userHeardView.findViewById(R.id.user_info_layout);
        userHeadImg = (ImageView) userHeardView.findViewById(R.id.user_head_img);
        userName = (TextView) userHeardView.findViewById(R.id.user_name);
        userLevelPro = (ProgressBar) userHeardView.findViewById(R.id.user_level_pro);
        userLevelLayout = (LinearLayout) userHeardView.findViewById(R.id.user_level_layout);
        userLevel = (TextView) userHeardView.findViewById(R.id.user_level);
        userExperience = (TextView) userHeardView.findViewById(R.id.user_experience);
        userExperienceHint = (TextView) userHeardView.findViewById(R.id.user_experience_hint);
        userPoints = (TextView) userHeardView.findViewById(R.id.user_points);

        View userContentView = View.inflate(getActivity(), R.layout.usercenter_home_content, null);
        vsMenuMessage = (ViewStub) userContentView.findViewById(R.id.vs_menu_message);
        vsMenuMyInfo = (ViewStub) userContentView.findViewById(R.id.vs_menu_my_info);
        vsMenuContact = (ViewStub) userContentView.findViewById(R.id.vs_menu_contact);
        vsMenuFans = (ViewStub) userContentView.findViewById(R.id.vs_menu_fans);
        vsMenuBlacklist = (ViewStub) userContentView.findViewById(R.id.vs_menu_blacklist);
        vsMenuSeekhelp = (ViewStub) userContentView.findViewById(R.id.vs_menu_seekhelp);
        vsMenuComment = (ViewStub) userContentView.findViewById(R.id.vs_menu_comment);
        vsMenuJoint = (ViewStub) userContentView.findViewById(R.id.vs_menu_joint);
        vsMenuFavor = (ViewStub) userContentView.findViewById(R.id.vs_menu_favor);
        vsMenuPublish = (ViewStub) userContentView.findViewById(R.id.vs_menu_publish);
        menuSettingBtn = (LinearLayout) userContentView.findViewById(R.id.menu_setting_btn);
        menuLogoutBtn = (Button) userContentView.findViewById(R.id.menu_logout_btn);

        userCenterBg = (ImageView) View.inflate(getActivity(), R.layout.usercenter_home_zoom, null);
        menuLogoutBtn.setOnClickListener(this);
        menuSettingBtn.setOnClickListener(this);
        homeScrollView.setHeaderView(userHeardView);
        homeScrollView.setZoomView(userCenterBg);
        homeScrollView.setScrollContentView(userContentView);
//        new AsyncTask<String, String, Bitmap>() {
//            @Override
//            protected Bitmap doInBackground(String... params) {
//                Bitmap pic = ProjectUtil.ToolPicture.gainViewBitmap(userCenterBg);
//                return ProjectUtil.ToolPicture.doBlur(pic, 88, true);
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                userCenterBg.setImageBitmap(bitmap);
//            }
//        }.execute();
        vsMenuBlacklist.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ManageAddressActivity.class));
            }
        });
        vsMenuComment.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuContact.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuFans.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuFavor.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuJoint.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuMessage.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuMyInfo.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuPublish.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });
        vsMenuSeekhelp.inflate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(getActivity(), CommitOrderActivity.class));
                ProjectUtil.T.show("^_^ 暂未实现");
            }
        });

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        homeScrollView.setHeaderLayoutParams(localObject);

    }

    @Override
    protected void setInitData() {
        mCurrentUser = BmobUser.getCurrentUser();
        menuLogoutBtn.setText(mCurrentUser == null ? "登陆" : "退出");
        menuLogoutBtn.setBackgroundResource(mCurrentUser == null ? R.color.colorPrimary : R.color.holo_red_light);
        userName.setText(mCurrentUser == null ? "请登陆" : mCurrentUser.getUsername());
        userLevel.setText("Lv.0");
        userExperience.setText("个性签名");
        userPoints.setText("男");
        showItem();
    }

    private void showItem() {
        vsMenuBlacklist.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuComment.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuContact.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuFans.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuFavor.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuJoint.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuMessage.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuMyInfo.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuPublish.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        vsMenuSeekhelp.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
        userLevelLayout.setVisibility(mCurrentUser == null ? View.GONE : View.VISIBLE);
    }


    private PullToZoomScrollViewEx homeScrollView;

    private ImageView userCenterBg;
    private LinearLayout userInfoLayout;
    private ImageView userHeadImg;
    private TextView userName;
    private ProgressBar userLevelPro;
    private LinearLayout userLevelLayout;
    private TextView userLevel;
    private TextView userExperience;
    private TextView userExperienceHint;

    private TextView userPoints;
    private ViewStub vsMenuMessage;
    private ViewStub vsMenuMyInfo;
    private ViewStub vsMenuContact;
    private ViewStub vsMenuFans;
    private ViewStub vsMenuBlacklist;
    private ViewStub vsMenuSeekhelp;
    private ViewStub vsMenuComment;
    private ViewStub vsMenuJoint;
    private ViewStub vsMenuFavor;
    private ViewStub vsMenuPublish;
    private LinearLayout menuSettingBtn;
    private Button menuLogoutBtn;

    @Override
    public void onClick(View v) {
        if (v == menuLogoutBtn) {
            if (mCurrentUser == null) {
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), 0);
            } else {
                mCurrentUser.logOut();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            setInitData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000);
            }
        } else if (v == menuSettingBtn) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setInitData();
        super.onActivityResult(requestCode, resultCode, data);
    }

}
