package com.example.android.chaoshi.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.GridViwAdapter;
import com.example.android.chaoshi.adapter.HorizontalListViewAdapter;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.bean.shop.Item3CShuMa;
import com.example.android.chaoshi.bean.shop.ItemTitlePager;
import com.example.android.chaoshi.model.Imp.DataModel;
import com.example.android.chaoshi.model.Imp.ImageLoadModel;
import com.example.android.chaoshi.model.Imp.ResultCallBack;
import com.example.android.chaoshi.ui.view.CustomGridView;
import com.example.android.chaoshi.ui.view.HorizontalListView;
import com.example.android.chaoshi.ui.view.MySlideMenu;
import com.example.android.chaoshi.ui.view.SlideMenu;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.ArrayList;
import java.util.List;


public class MuliActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    //侧滑菜单控件
    private SlideMenu mMainSlideMenu;
    private MySlideMenu mPhoneSlideMenu;
    private MySlideMenu mComputerSlideMenu;
    private MySlideMenu mDigitalSlideMenu;
    private MySlideMenu mAppliancesSlideMenu;
    private MySlideMenu mHouseholdSlideMenu;
    //菜单栏侧滑点击控件
    private ImageView ivTitleBarMenu;
    //各楼层侧滑点击控件
    private ImageView ivPhoneOut;
    private ImageView ivPhoneBack;
    private ImageView ivComputerOut;
    private ImageView ivComputerBack;
    private ImageView ivDigitalOut;
    private ImageView ivDigitalBack;
    private ImageView ivAppliancesOut;
    private ImageView ivAppliancesBack;
    private ImageView ivHouseholdOut;
    private ImageView ivHouseholdBack;
    //小图标选择点击按钮
    private TextView tvPhonePhone;
    private TextView tvPhoneParts;
    private TextView tvComputerMachine;
    private TextView tvComputerStorage;
    private TextView tvComputerOffice;
    private TextView tvDigitalApple;
    private TextView tvDigitalCameta;
    private TextView tvDigitalVideo;
    private TextView tvAppiancesKitchen;
    private TextView tvAppiancesLife;
    private TextView tvAppiancesCare;
    private TextView tvHouseHoldRefrigerator;
    private TextView tvHouseHoldTelevision;
    private TextView tvHouseHoldConditoner;
    //各标题显示控件
    private TextView tvPhoneTitel;
    private TextView tvComputerTitel;
    private TextView tvDigitalTitel;
    private TextView tvAppliancesTitel;
    private TextView tvHouseHoldTitel;

    //分类中文本
    private TextView tvClassificatonPhone;
    private TextView tvClassificatonComputer;
    private TextView tvClassificatonDigital;
    private TextView tvClassificatonCamera;
    private TextView tvClassificatonStorage;
    private TextView tvClassificatonOffice;
    private TextView tvClassificatonKitchen;
    private TextView tvClassificatonLife;
    private TextView tvClassificatonCare;
    private TextView tvClassificatonHouseHold;
    //小图标辩标题栏的线性布局
    private LinearLayout linearLayoutPhone;
    private LinearLayout linearLayoutComputer;
    private LinearLayout linearLayoutDigital;
    private LinearLayout linearLayoutAppliances;
    private LinearLayout linearLayoutHouseHold;
    //横向的listview控件
    private HorizontalListView horizontalListViewPhone;
    private HorizontalListView horizontalListViewComputer;
    private HorizontalListView horizontalListViewDigital;
    private HorizontalListView horizontalListViewAppliances;
    private HorizontalListView horizontalListViewHouseHold;
    //表格控件
    private CustomGridView gridViewPhone;
    private CustomGridView gridViewComputer;
    private CustomGridView gridViewDigital;
    private CustomGridView gridViewAppliances;
    private CustomGridView gridViewHouseHold;
    //存储电话楼层相关数据的集合
    private List<Item3CShuMa> phone_s = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> phoneList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> phonePhoneList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> phonePartsList = new ArrayList<Item3CShuMa>();
    //存储电脑楼层相关数据的集合
    private List<Item3CShuMa> computer_s = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> computerList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> computerMachineList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> computerStorageList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> computerOfficeList = new ArrayList<Item3CShuMa>();
    //存储数码楼层相关数据的集合
    private List<Item3CShuMa> digital_s = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> digitalList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> digitalAppleList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> digitalCameraList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> digitalVideoList = new ArrayList<Item3CShuMa>();
    //存储小家电楼层相关数据的集合
    private List<Item3CShuMa> appliances_s = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> appliancesList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> appliancesKitchenList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> appliancesLifeList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> appliancesCareList = new ArrayList<Item3CShuMa>();
    //存储大家电楼层相关数据的集合
    private List<Item3CShuMa> houseHold_s = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> houseHoldList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> houseHoldRefrigetatorList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> houseHoldTelevitionList = new ArrayList<Item3CShuMa>();
    private List<Item3CShuMa> houseHoldConditionerList = new ArrayList<Item3CShuMa>();

    private List<ItemTitlePager> titles = new ArrayList<>();

    //为横向的listview配置的adapter
    private HorizontalListViewAdapter phoneHorizontalAdapter;
    private HorizontalListViewAdapter computerHorizontalAdapter;
    private HorizontalListViewAdapter digitalHorizontalAdapter;
    private HorizontalListViewAdapter appliancesHorizontalAdapter;
    private HorizontalListViewAdapter householdHorizontalAdapter;
    //为grideview配置的adapter
    private GridViwAdapter phoneGridAdapter;
    private GridViwAdapter computerGridAdapter;
    private GridViwAdapter digitalGridAdapter;
    private GridViwAdapter appliancesGridAdapter;
    private GridViwAdapter householdGridAdapter;

    private ProgressBar progressBar;
    private ViewPager viewPagerThemeScene;

    private DataModel dataModel;
    private ImageLoadModel imageloadModel;
    private String result;
    private CatLoadingView mDiaLog;


    @Override
    protected void onLCreate() {
        dataModel = new DataModel(this);
        imageloadModel = new ImageLoadModel(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main_3c;
    }

    /**
     * 配置viewpager的adapter
     */
    private void setviewPagerAdapter() {
        final List<View> views = new ArrayList<>();
        View v1 = View.inflate(this, R.layout.viewpager_item, null);
        ImageView ivThemeScene1 = (ImageView) v1.findViewById(R.id.iv_theme_scene_content);
        ivThemeScene1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = titles.get(0).getHref();
                startJump(url);
            }
        });
        imageloadModel.imageLoad(titles.get(0).getImg(), ivThemeScene1, 0, 0);

        View v2 = View.inflate(this, R.layout.viewpager_item, null);
        ImageView ivThemeScene2 = (ImageView) v2.findViewById(R.id.iv_theme_scene_content);
        ivThemeScene2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = titles.get(1).getHref();
                startJump(url);
            }
        });
        imageloadModel.imageLoad(titles.get(1).getImg(), ivThemeScene2, 0, 0);

        View v3 = View.inflate(this, R.layout.viewpager_item, null);
        ImageView ivThemeScene3 = (ImageView) v3.findViewById(R.id.iv_theme_scene_content);
        ivThemeScene3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = titles.get(2).getHref();
                startJump(url);
            }
        });
        imageloadModel.imageLoad(titles.get(2).getImg(), ivThemeScene3, 0, 0);
        views.add(v1);
        views.add(v2);
        views.add(v3);

        PagerAdapter viewpagerAdapter = new PagerAdapter() {
            //将第position个对象从容器中移除
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View v = views.get(position);
                container.removeView(v);
            }

            //将第position个对象添加到容器中
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v = views.get(position);
                container.addView(v);
                return v;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        viewPagerThemeScene.setAdapter(viewpagerAdapter);
    }

    /**
     * 拿到网站返回的字符串，保存到成员变量中
     */
    private void getData() {
        ivTitleBarMenu.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        mMainSlideMenu.setVisibility(View.GONE);
        dataModel.getStringData(new ResultCallBack() {
            @Override
            public void onLoadData(String result) {
                progressBar.setVisibility(View.GONE);
                if (result == null) {
                    Toast.makeText(MuliActivity.this, "请检查网络设置", Toast.LENGTH_SHORT).show();
                } else {
                    mMainSlideMenu.setVisibility(View.VISIBLE);
                    ivTitleBarMenu.setEnabled(true);
                    MuliActivity.this.result = result;

                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            setData();
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            onPaseOk();

                        }
                    }.execute();
                }
            }
        });
    }

    /**
     * 通过网络拿回的字符串进行解析获得相应的数据集合
     */
    private void setData() {
        titles = dataModel.getParser3CTitlePagerList(result, 0);
        //用于存储数据的集合赋值
        phone_s = dataModel.getParser3CShuMaList(result, 1);
        phonePhoneList = dataModel.getParser3CShuMaList(result, 2);
        phonePartsList = dataModel.getParser3CShuMaList(result, 3);

        computer_s = dataModel.getParser3CShuMaList(result, 4);
        computerMachineList = dataModel.getParser3CShuMaList(result, 5);
        computerStorageList = dataModel.getParser3CShuMaList(result, 6);
        computerOfficeList = dataModel.getParser3CShuMaList(result, 7);

        digital_s = dataModel.getParser3CShuMaList(result, 8);
        digitalAppleList = dataModel.getParser3CShuMaList(result, 9);
        digitalCameraList = dataModel.getParser3CShuMaList(result, 10);
        digitalVideoList = dataModel.getParser3CShuMaList(result, 11);

        appliances_s = dataModel.getParser3CShuMaList(result, 12);
        appliancesKitchenList = dataModel.getParser3CShuMaList(result, 13);
        appliancesLifeList = dataModel.getParser3CShuMaList(result, 14);
        appliancesCareList = dataModel.getParser3CShuMaList(result, 15);

        houseHold_s = dataModel.getParser3CShuMaList(result, 16);
        houseHoldRefrigetatorList = dataModel.getParser3CShuMaList(result, 17);
        houseHoldTelevitionList = dataModel.getParser3CShuMaList(result, 18);
        houseHoldConditionerList = dataModel.getParser3CShuMaList(result, 19);
        //用于创建adapter的集合赋值
        phoneList.clear();
        phoneList.addAll(phonePhoneList);
        computerList.clear();
        computerList.addAll(computerMachineList);
        digitalList.clear();
        digitalList.addAll(digitalAppleList);
        appliancesList.clear();
        appliancesList.addAll(appliancesKitchenList);
        houseHoldList.clear();
        houseHoldList.addAll(houseHoldRefrigetatorList);
    }

    public void onPaseOk() {
        setAdapter();
        setviewPagerAdapter();
        mDiaLog.dismiss();
    }

    /**
     * 创建adapter对象，并为横向listview和grideview配置adapter
     */
    private void setAdapter() {
        //创建adapter对象
        phoneHorizontalAdapter = new HorizontalListViewAdapter(this, phone_s);
        computerHorizontalAdapter = new HorizontalListViewAdapter(this, computer_s);
        digitalHorizontalAdapter = new HorizontalListViewAdapter(this, digital_s);
        appliancesHorizontalAdapter = new HorizontalListViewAdapter(this, appliances_s);
        householdHorizontalAdapter = new HorizontalListViewAdapter(this, houseHold_s);

        phoneGridAdapter = new GridViwAdapter(MuliActivity.this, phoneList);
        computerGridAdapter = new GridViwAdapter(MuliActivity.this, computerList);
        digitalGridAdapter = new GridViwAdapter(MuliActivity.this, digitalList);
        appliancesGridAdapter = new GridViwAdapter(MuliActivity.this, appliancesList);
        householdGridAdapter = new GridViwAdapter(MuliActivity.this, houseHoldList);
//配置adapter
        horizontalListViewPhone.setAdapter(phoneHorizontalAdapter);
        horizontalListViewComputer.setAdapter(computerHorizontalAdapter);
        horizontalListViewDigital.setAdapter(digitalHorizontalAdapter);
        horizontalListViewAppliances.setAdapter(appliancesHorizontalAdapter);
        horizontalListViewHouseHold.setAdapter(householdHorizontalAdapter);

        Log.d("hua", phonePhoneList.toString());
        gridViewPhone.setAdapter(phoneGridAdapter);
        gridViewComputer.setAdapter(computerGridAdapter);
        gridViewDigital.setAdapter(digitalGridAdapter);
        gridViewAppliances.setAdapter(appliancesGridAdapter);
        gridViewHouseHold.setAdapter(householdGridAdapter);
    }

    /**
     * 为需要配置监听器的控件设置监听器
     */
    private void setListener() {
        ivTitleBarMenu.setOnClickListener(this);
        ivPhoneOut.setOnClickListener(this);
        ivPhoneBack.setOnClickListener(this);
        ivComputerOut.setOnClickListener(this);
        ivComputerBack.setOnClickListener(this);
        ivDigitalOut.setOnClickListener(this);
        ivDigitalBack.setOnClickListener(this);
        ivAppliancesOut.setOnClickListener(this);
        ivAppliancesBack.setOnClickListener(this);
        ivHouseholdOut.setOnClickListener(this);
        ivHouseholdBack.setOnClickListener(this);

        tvPhoneParts.setOnClickListener(this);
        tvPhonePhone.setOnClickListener(this);
        tvComputerMachine.setOnClickListener(this);
        tvComputerStorage.setOnClickListener(this);
        tvComputerOffice.setOnClickListener(this);
        tvDigitalApple.setOnClickListener(this);
        tvDigitalCameta.setOnClickListener(this);
        tvDigitalVideo.setOnClickListener(this);
        tvAppiancesKitchen.setOnClickListener(this);
        tvAppiancesLife.setOnClickListener(this);
        tvAppiancesCare.setOnClickListener(this);
        tvHouseHoldRefrigerator.setOnClickListener(this);
        tvHouseHoldTelevision.setOnClickListener(this);
        tvHouseHoldConditoner.setOnClickListener(this);

        tvPhoneParts.setOnClickListener(this);
        tvPhonePhone.setOnClickListener(this);
        tvComputerMachine.setOnClickListener(this);
        tvComputerStorage.setOnClickListener(this);
        tvComputerOffice.setOnClickListener(this);
        tvDigitalApple.setOnClickListener(this);
        tvDigitalCameta.setOnClickListener(this);
        tvDigitalVideo.setOnClickListener(this);
        tvAppiancesKitchen.setOnClickListener(this);
        tvAppiancesLife.setOnClickListener(this);
        tvAppiancesCare.setOnClickListener(this);
        tvHouseHoldRefrigerator.setOnClickListener(this);
        tvHouseHoldTelevision.setOnClickListener(this);
        tvHouseHoldConditoner.setOnClickListener(this);

        tvClassificatonPhone.setOnClickListener(this);
        tvClassificatonComputer.setOnClickListener(this);
        tvClassificatonDigital.setOnClickListener(this);
        tvClassificatonCamera.setOnClickListener(this);
        tvClassificatonStorage.setOnClickListener(this);
        tvClassificatonOffice.setOnClickListener(this);
        tvClassificatonKitchen.setOnClickListener(this);
        tvClassificatonLife.setOnClickListener(this);
        tvClassificatonCare.setOnClickListener(this);
        tvClassificatonHouseHold.setOnClickListener(this);

        //adapterview条目点击监听器
        horizontalListViewPhone.setOnItemClickListener(this);
        horizontalListViewComputer.setOnItemClickListener(this);
        horizontalListViewDigital.setOnItemClickListener(this);
        horizontalListViewAppliances.setOnItemClickListener(this);
        horizontalListViewHouseHold.setOnItemClickListener(this);

        gridViewPhone.setOnItemClickListener(this);
        gridViewComputer.setOnItemClickListener(this);
        gridViewDigital.setOnItemClickListener(this);
        gridViewAppliances.setOnItemClickListener(this);
        gridViewHouseHold.setOnItemClickListener(this);

    }


    /**
     * 点击监听器
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //主侧滑菜单的滑入滑出
            case R.id.title_bar_menu_btn:

                if (mMainSlideMenu.isMainScreenShowing()) {
                    mMainSlideMenu.openMenu();
                } else {
                    mMainSlideMenu.closeMenu();
                }
                break;
            //楼层的滑入滑出点击控件
            case R.id.iv_phone_out:
                mPhoneSlideMenu.openMenu();
                tvPhoneTitel.setVisibility(View.GONE);
                linearLayoutPhone.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_phone_back:
                mPhoneSlideMenu.closeMenu();
                tvPhoneTitel.setVisibility(View.VISIBLE);
                linearLayoutPhone.setVisibility(View.GONE);
                break;

            case R.id.iv_computer_out:
                mComputerSlideMenu.openMenu();
                tvComputerTitel.setVisibility(View.GONE);
                linearLayoutComputer.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_computer_back:
                mComputerSlideMenu.closeMenu();
                tvComputerTitel.setVisibility(View.VISIBLE);
                linearLayoutComputer.setVisibility(View.GONE);
                break;

            case R.id.iv_digital_out:
                mDigitalSlideMenu.openMenu();
                tvDigitalTitel.setVisibility(View.GONE);
                linearLayoutDigital.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_digital_back:
                mDigitalSlideMenu.closeMenu();
                tvDigitalTitel.setVisibility(View.VISIBLE);
                linearLayoutDigital.setVisibility(View.GONE);
                break;

            case R.id.iv_appliances_out:
                mAppliancesSlideMenu.openMenu();
                tvAppliancesTitel.setVisibility(View.GONE);
                linearLayoutAppliances.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_appliances_back:
                mAppliancesSlideMenu.closeMenu();
                tvAppliancesTitel.setVisibility(View.VISIBLE);
                linearLayoutAppliances.setVisibility(View.GONE);
                break;

            case R.id.iv_household_out:
                mHouseholdSlideMenu.openMenu();
                tvHouseHoldTitel.setVisibility(View.GONE);
                linearLayoutHouseHold.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_household_back:
                mHouseholdSlideMenu.closeMenu();
                tvHouseHoldTitel.setVisibility(View.VISIBLE);
                linearLayoutHouseHold.setVisibility(View.GONE);
                break;

            //小图标分类的图标点击控件
            case R.id.tv_phone_phone:
                phoneList.clear();
                phoneList.addAll(phonePhoneList);
                phoneGridAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_phone_parts:
                phoneList.clear();
                phoneList.addAll(phonePartsList);
                phoneGridAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_computer_machine:
                computerList.clear();
                computerList.addAll(computerMachineList);
                computerGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_computer_storage:
                computerList.clear();
                computerList.addAll(computerStorageList);
                computerGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_computer_office:
                computerList.clear();
                computerList.addAll(computerOfficeList);
                computerGridAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_digital_apple:
                digitalList.clear();
                digitalList.addAll(digitalAppleList);
                digitalGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_digital_camera:
                digitalList.clear();
                digitalList.addAll(digitalCameraList);
                digitalGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_digital_video:
                digitalList.clear();
                digitalList.addAll(digitalVideoList);
                digitalGridAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_appliances_kitchen:
                appliancesList.clear();
                appliancesList.addAll(appliancesKitchenList);
                appliancesGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_appliances_life:
                appliancesList.clear();
                appliancesList.addAll(appliancesLifeList);
                appliancesGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_appliances_care:
                appliancesList.clear();
                appliancesList.addAll(appliancesCareList);
                appliancesGridAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_household_refrigerator:
                houseHoldList.clear();
                houseHoldList.addAll(houseHoldRefrigetatorList);
                householdGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_household_television:
                houseHoldList.clear();
                houseHoldList.addAll(houseHoldTelevitionList);
                householdGridAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_household_conditioner:
                houseHoldList.clear();
                houseHoldList.addAll(houseHoldConditionerList);
                householdGridAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_classification_phone:
                String phone = tvClassificatonPhone.getText().toString();
                reserch(phone);
                break;
            case R.id.tv_classification_computer:
                String computer = tvClassificatonComputer.getText().toString();
                reserch(computer);
                break;
            case R.id.tv_classification_digital:
                String digital = tvClassificatonDigital.getText().toString();
                reserch(digital);
                break;
            case R.id.tv_classification_camera:
                String camera = tvClassificatonCamera.getText().toString();
                reserch(camera);
                break;
            case R.id.tv_classification_storage:
                String storage = tvClassificatonStorage.getText().toString();
                reserch(storage);
                break;
            case R.id.tv_classification_office:
                String office = tvClassificatonOffice.getText().toString();
                reserch(office);
                break;
            case R.id.tv_classification_kitchen:
                String kitchen = tvClassificatonKitchen.getText().toString();
                reserch(kitchen);
                break;
            case R.id.tv_classification_life:
                String life = tvClassificatonLife.getText().toString();
                reserch(life);
                break;
            case R.id.tv_classification_care:
                String care = tvClassificatonCare.getText().toString();
                reserch(care);
                break;
            case R.id.tv_classification_household:
                String house = tvClassificatonHouseHold.getText().toString();
                reserch(house);
                break;
        }

    }


    /**
     * adaperview条目点击监听器
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item3CShuMa shuMa = null;
        switch (parent.getId()) {
            case R.id.horizontal_phone:
                shuMa = phone_s.get(position);
                break;

            case R.id.horizontal_computer:
                shuMa = computer_s.get(position);
                break;

            case R.id.horizontal_digital:
                shuMa = digital_s.get(position);
                break;

            case R.id.horizontal_appliances:
                shuMa = appliances_s.get(position);
                break;

            case R.id.horizontal_household:
                shuMa = houseHold_s.get(position);
                break;

            case R.id.gridview_phone:
                shuMa = phoneList.get(position);
                break;

            case R.id.gridview_computer:
                shuMa = computerList.get(position);
                break;

            case R.id.gridview_digital:
                shuMa = digitalList.get(position);
                break;

            case R.id.gridview_appliances:
                shuMa = appliancesList.get(position);
                break;

            case R.id.gridview_household:
                shuMa = houseHoldList.get(position);
                break;

        }
        startJump(shuMa);

    }

    /**
     * 跳转到搜索页面
     *
     * @param reserch
     */
    private void reserch(String reserch) {
        Toast.makeText(this, reserch, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Search2Activity.class);
        intent.putExtra("search_type", reserch);
        startActivity(intent);
    }

    /**
     * 跳转界面
     *
     * @param shuMa
     */
    private void startJump(Item3CShuMa shuMa) {
        startJump(shuMa.getHref());
    }

    /**
     * 重载的方法
     * 跳转界面
     *
     * @param
     */
    private void startJump(String url) {
        mMainSlideMenu.closeMenu();
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);

    }

    /**
     * 滑动监听器，当滑动状态发生改变时
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (view.getId()) {
            case R.id.gridview_phone:
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        if (gridViewPhone.getIsUp()) {
                            if (isBottom) {
//                                int[] viewLocation = new int[2];
//                                view.getLocationOnScreen(viewLocation);
//                                int parentY = viewLocation[1]+view.getMeasuredHeight();
//
//                                View v = view.getChildAt(view.getLastVisiblePosition());
//                                int [] last = new int[2];
//                                v.getLocationOnScreen(last);
//                                int y = last[1] + v.getMeasuredHeight();
//                                if(parentY==y){
//                                    gridViewPhone.setRequest(false);
//                                }
//                                Toast.makeText(this, "到底了", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            gridViewPhone.setRequest(true);

                        }
                        break;
                }
                break;
        }

    }

    private boolean isBottom;
    private boolean isUp;

    /**
     * 滑动监听器，当滑动时
     *
     * @param
     * @param
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isBottom = true;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMainSlideMenu != null) {
            mMainSlideMenu.closeMenu();
        }
    }

    public void initView() {
        ivTitleBarMenu = (ImageView) findViewById(R.id.title_bar_menu_btn);
        ivPhoneBack = (ImageView) findViewById(R.id.iv_phone_back);
        ivPhoneOut = (ImageView) findViewById(R.id.iv_phone_out);
        ivComputerOut = (ImageView) findViewById(R.id.iv_computer_out);
        ivComputerBack = (ImageView) findViewById(R.id.iv_computer_back);
        ivDigitalOut = (ImageView) findViewById(R.id.iv_digital_out);
        ivDigitalBack = (ImageView) findViewById(R.id.iv_digital_back);
        ivAppliancesOut = (ImageView) findViewById(R.id.iv_appliances_out);
        ivAppliancesBack = (ImageView) findViewById(R.id.iv_appliances_back);
        ivHouseholdOut = (ImageView) findViewById(R.id.iv_household_out);
        ivHouseholdBack = (ImageView) findViewById(R.id.iv_household_back);

        mMainSlideMenu = (SlideMenu) findViewById(R.id.slide_menu);
        mPhoneSlideMenu = (MySlideMenu) findViewById(R.id.slidemenu_phone);
        mComputerSlideMenu = (MySlideMenu) findViewById(R.id.slidemenu_computer);
        mDigitalSlideMenu = (MySlideMenu) findViewById(R.id.slidemenu_digital);
        mAppliancesSlideMenu = (MySlideMenu) findViewById(R.id.slidemenu_appliances);
        mHouseholdSlideMenu = (MySlideMenu) findViewById(R.id.slidemenu_household);

        horizontalListViewPhone = (HorizontalListView) findViewById(R.id.horizontal_phone);
        horizontalListViewComputer = (HorizontalListView) findViewById(R.id.horizontal_computer);
        horizontalListViewDigital = (HorizontalListView) findViewById(R.id.horizontal_digital);
        horizontalListViewAppliances = (HorizontalListView) findViewById(R.id.horizontal_appliances);
        horizontalListViewHouseHold = (HorizontalListView) findViewById(R.id.horizontal_household);
        gridViewPhone = (CustomGridView) findViewById(R.id.gridview_phone);
        gridViewComputer = (CustomGridView) findViewById(R.id.gridview_computer);
        gridViewDigital = (CustomGridView) findViewById(R.id.gridview_digital);
        gridViewAppliances = (CustomGridView) findViewById(R.id.gridview_appliances);
        gridViewHouseHold = (CustomGridView) findViewById(R.id.gridview_household);

        tvPhoneParts = (TextView) findViewById(R.id.tv_phone_parts);
        tvPhonePhone = (TextView) findViewById(R.id.tv_phone_phone);
        tvComputerMachine = (TextView) findViewById(R.id.tv_computer_machine);
        tvComputerStorage = (TextView) findViewById(R.id.tv_computer_storage);
        tvComputerOffice = (TextView) findViewById(R.id.tv_computer_office);
        tvDigitalApple = (TextView) findViewById(R.id.tv_digital_apple);
        tvDigitalCameta = (TextView) findViewById(R.id.tv_digital_camera);
        tvDigitalVideo = (TextView) findViewById(R.id.tv_digital_video);
        tvAppiancesKitchen = (TextView) findViewById(R.id.tv_appliances_kitchen);
        tvAppiancesLife = (TextView) findViewById(R.id.tv_appliances_life);
        tvAppiancesCare = (TextView) findViewById(R.id.tv_appliances_care);
        tvHouseHoldRefrigerator = (TextView) findViewById(R.id.tv_household_refrigerator);
        tvHouseHoldTelevision = (TextView) findViewById(R.id.tv_household_television);
        tvHouseHoldConditoner = (TextView) findViewById(R.id.tv_household_conditioner);

        tvClassificatonPhone = (TextView) findViewById(R.id.tv_classification_phone);
        tvClassificatonComputer = (TextView) findViewById(R.id.tv_classification_computer);
        tvClassificatonDigital = (TextView) findViewById(R.id.tv_classification_digital);
        tvClassificatonCamera = (TextView) findViewById(R.id.tv_classification_camera);
        tvClassificatonStorage = (TextView) findViewById(R.id.tv_classification_storage);
        tvClassificatonOffice = (TextView) findViewById(R.id.tv_classification_office);
        tvClassificatonKitchen = (TextView) findViewById(R.id.tv_classification_kitchen);
        tvClassificatonLife = (TextView) findViewById(R.id.tv_classification_life);
        tvClassificatonCare = (TextView) findViewById(R.id.tv_classification_care);
        tvClassificatonHouseHold = (TextView) findViewById(R.id.tv_classification_household);

        tvPhoneTitel = (TextView) findViewById(R.id.tv_phone_title);
        tvComputerTitel = (TextView) findViewById(R.id.tv_computer_title);
        tvDigitalTitel = (TextView) findViewById(R.id.tv_digital_title);
        tvAppliancesTitel = (TextView) findViewById(R.id.tv_appliances_title);
        tvHouseHoldTitel = (TextView) findViewById(R.id.tv_household_title);

        linearLayoutPhone = (LinearLayout) findViewById(R.id.linearlayout_phone_title);
        linearLayoutComputer = (LinearLayout) findViewById(R.id.linearlayout_computer_title);
        linearLayoutDigital = (LinearLayout) findViewById(R.id.linearlayout_digital_title);
        linearLayoutAppliances = (LinearLayout) findViewById(R.id.linearlayout_appliances_title);
        linearLayoutHouseHold = (LinearLayout) findViewById(R.id.linearlayout_household_title);

        viewPagerThemeScene = (ViewPager) findViewById(R.id.viewpager_theme_scene);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        mDiaLog = new CatLoadingView();
        mDiaLog.show(getSupportFragmentManager(), "");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("返回");

    }

    @Override
    protected void initData() {
        //设置监听器
        setListener();

        //获得数据
        getData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
