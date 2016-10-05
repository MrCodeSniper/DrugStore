package com.example.android.chaoshi.ui.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.ManageAddressAdapter;
import com.example.android.chaoshi.adapter.WareOrderAdapter;
import com.example.android.chaoshi.adapter.layoutmanager.FullyLinearLayoutManager;
import com.example.android.chaoshi.base.BaseActivity;
import com.example.android.chaoshi.entity.Address;
import com.example.android.chaoshi.entity.Wares;
import com.example.android.chaoshi.util.DBOpenHelper;
import com.example.android.chaoshi.util.DatabaseUtil;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */

public class CommitActivity extends BaseActivity implements View.OnClickListener{

    /**
     * 银联支付渠道
     */
    private static final String CHANNEL_UPACP = "upacp";
    /**
     * 微信支付渠道
     */
    private static final String CHANNEL_WECHAT = "wx";
    /**
     * 支付支付渠道
     */
    private static final String CHANNEL_ALIPAY = "alipay";
    /**
     * 百度支付渠道
     */
    private static final String CHANNEL_BFB = "bfb";
    /**
     * 京东支付渠道
     */
    private static final String CHANNEL_JDPAY_WAP = "jdpay_wap";

    private String payChannel = CHANNEL_ALIPAY;


    @ViewInject(R.id.txt_order)
    private TextView txtOrder;

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.rl_addr)
    private RelativeLayout rl_addr;


    @ViewInject(R.id.rl_alipay)
    private RelativeLayout mLayoutAlipay;

    @ViewInject(R.id.rl_wechat)
    private RelativeLayout mLayoutWechat;

    @ViewInject(R.id.rl_bd)
    private RelativeLayout mLayoutBd;


    @ViewInject(R.id.rb_alipay)
    private RadioButton mRbAlipay;

    @ViewInject(R.id.rb_webchat)
    private RadioButton mRbWechat;

    @ViewInject(R.id.rb_bd)
    private RadioButton mRbBd;

    @ViewInject(R.id.btn_createOrder)
    private Button mBtnCreateOrder;

    @ViewInject(R.id.txt_total)
    private TextView mTxtTotal;

    private Wares wares;

    private List<Wares> list;
    private HashMap<String,RadioButton> channels = new HashMap<>(3);

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    private Address defaultAddress;




    private static List<Address> addresses;

    private static final int CHOOSE_ADDRESS=100;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void initView() {
      initActionbar();
        list=new ArrayList<>();
        channels.put(CHANNEL_ALIPAY,mRbAlipay);
        channels.put(CHANNEL_WECHAT,mRbWechat);
        channels.put(CHANNEL_BFB,mRbBd);

        mLayoutAlipay.setOnClickListener(this);
        mLayoutWechat.setOnClickListener(this);
        mLayoutBd.setOnClickListener(this);
        rl_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CommitActivity.this, ManageAddressActivity.class);
                startActivityForResult(intent,CHOOSE_ADDRESS);
            }
        });

        Serializable ware = getIntent().getSerializableExtra("ware");
        if(ware==null){
            finish();
        }else {
            wares=(Wares) ware;
            list.add(wares);
            mTxtTotal.setText("实付： ￥"+wares.getPrice());
            FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
            layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(new WareOrderAdapter(this,list));
        }

       showDefalutAdd();


    }

    @Override
    protected void initData() {

    }


    private void  initActionbar(){
        getSupportActionBar().hide();//隐藏掉整个ActionBar，包括下面的Tabs
    }


    @Override
    public void onClick(View v) {
            selectPayChannle(v.getTag().toString());
    }

    public void selectPayChannle(String paychannel){

        for (Map.Entry<String,RadioButton> entry:channels.entrySet()){

            payChannel = paychannel;
            RadioButton rb = entry.getValue();
            if(entry.getKey().equals(paychannel)){

                boolean isCheck = rb.isChecked();
                rb.setChecked(!isCheck);

            }
            else
                rb.setChecked(false);
        }

    }


    private void showDefaultAddress() {

        try {
            DatabaseUtil dbUtil = new DatabaseUtil(this);
            String whereClause = "isDefault";
            String[] whereArgs = {1+""};

            List<Address> queryAddress = dbUtil.query(whereClause, whereArgs);

            if (queryAddress != null) {
                defaultAddress = queryAddress.get(0);
                Log.e("taz","数据库中有地址"+defaultAddress);
//                tvReceiveAddress.setText("姓名："+defaultAddress.getName()+"\n电话："+defaultAddress.getPhoneNumber()+"\n地址"+defaultAddress.getAddrezz());
                tv_address.setText(defaultAddress.getAddrezz());
                tv_name.setText(defaultAddress.getName()+"("+defaultAddress.getPhoneNumber()+")");

            }else {
                Log.e("taz","数据库中没有地址");
                tv_name.setText("");
                tv_address.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void showDefalutAdd() {
        try {

            DatabaseUtil databaseUtil = new DatabaseUtil(this);
            addresses = databaseUtil.query(null, null);
            if (addresses == null||addresses.size()==0) {
                Log.e("taz","数据库中没有地址");
                Log.e("taz","数据库中没有地址");
                tv_name.setText("很抱歉");
                tv_address.setText("您还没有默认地址呢,请点击设置");
            } else {
                for (Address address : addresses) {
                    if (address.isDefault() == 1) {
                        Log.e("taz","找到默认地址");
                        defaultAddress=address;

                        tv_address.setText(defaultAddress.getAddrezz());
                        tv_name.setText(defaultAddress.getName() + "(" + defaultAddress.getPhoneNumber() + ")");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Address address = (Address) data.getExtras().get("address");
            tv_address.setText(address.getAddrezz());
            tv_name.setText(address.getName() + "(" + address.getPhoneNumber() + ")");

        }


    }
}
