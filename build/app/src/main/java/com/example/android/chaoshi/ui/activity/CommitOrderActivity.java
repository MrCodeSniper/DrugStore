package com.example.android.chaoshi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.Address;
import com.example.android.chaoshi.util.DBOpenHelper;
import com.example.android.chaoshi.util.DatabaseUtil;

import java.util.List;

public class CommitOrderActivity extends Activity implements OnClickListener {

	private LinearLayout llAddress;
	private TextView tvReceiveAddress;
	private TextView tvBack;
	private Address defaultAddress;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commit_order);
		
		db = new DBOpenHelper(this).getWritableDatabase();
		
		initViews();
		showDefaultAddress();
		setListener();
	}

	private void showDefaultAddress() {
		try {
			DatabaseUtil dbUtil = new DatabaseUtil(this);
			String whereClause = "isDefault";
			String[] whereArgs = {1+""};
			List<Address> queryAddress = dbUtil.query(whereClause, whereArgs);
			
			if (queryAddress != null) {
				defaultAddress = queryAddress.get(0);
				tvReceiveAddress.setText("姓名："+defaultAddress.getName()+"\n电话："+defaultAddress.getPhoneNumber()+"\n地址"+defaultAddress.getAddrezz());
			}else {
				tvReceiveAddress.setText("\n请添加地址\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setListener() {
		llAddress.setOnClickListener(this);
		tvBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back_main:
			finish();
			break;
		case R.id.ll_address_main:
			Intent i = new Intent(this,ManageAddressActivity.class);
			startActivityForResult(i,1);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			Address address = (Address) data.getExtras().get("address");
			tvReceiveAddress.setText("姓名："+address.getName()+"\n电话："+address.getPhoneNumber()+"\n地址"+address.getAddrezz());
		}
	}
	
	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}
	
	private void initViews() {
		tvReceiveAddress = (TextView) findViewById(R.id.tv_receive_address_main);
		llAddress = (LinearLayout) findViewById(R.id.ll_address_main);
		tvBack = (TextView) findViewById(R.id.tv_back_main);
	}
}
