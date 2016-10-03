package com.example.android.chaoshi.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.adapter.ManageAddressAdapter;
import com.example.android.chaoshi.entity.Address;
import com.example.android.chaoshi.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class ManageAddressActivity extends Activity implements OnClickListener,OnItemClickListener {
	private TextView tvBack;
	private ListView lvAddress;
	private TextView tvAdd;
	private static List<Address> addresses;
	private ManageAddressAdapter adapter;
	private BroadcastReceiver receiver;
	private static final String ACTION_EDIT_ADDRESS_COMPLETE = "cn.sakura.ACTION_EDIT_ADDRESS_COMPLETE";
	private static final String ACTION_DELETE_ADDRESS = "cn.sakura.ACTION_DELETE_ADDRESS";
	public static long count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_address);

		initViews();

		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_EDIT_ADDRESS_COMPLETE);
		filter.addAction(ACTION_DELETE_ADDRESS);
		registerReceiver(receiver, filter);
		setListeners();
		showAddresses();

	}

	public static int getDefaultAddressPosition() {
		for (int i = 0; i < addresses.size(); i++) {
			if ((addresses.get(i)).isDefault() == 1) {
				return i;
			}
		}
		return 0;
	}

	private class InnerReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (ACTION_EDIT_ADDRESS_COMPLETE.equals(action)) {
				//int position = intent.getIntExtra("position", -1);
				//Address address = (Address) intent.getExtras().get("address");
				addresses.clear();
				showAddresses();
				adapter.notifyDataSetChanged();
			}else if (ACTION_DELETE_ADDRESS.equals(action)) {
				Intent i = new Intent();
				int deleteAddressPosition = i.getIntExtra("position", -1);
				addresses.remove(deleteAddressPosition);
				adapter.notifyDataSetChanged();
			}
		}
	}

	public static Address getAddressByPosition(int position) {
		return addresses.get(position);
	}

	private void showAddresses() {
		try {
			DatabaseUtil databaseUtil = new DatabaseUtil(this);
			addresses = databaseUtil.query(null, null);
			count = databaseUtil.getCaseNum();
			setAdapter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setAdapter() {
		adapter = new ManageAddressAdapter(this, addresses);
		lvAddress.setAdapter(adapter);
	}

	private void setListeners() {
		tvBack.setOnClickListener(this);
		tvAdd.setOnClickListener(this);	
		lvAddress.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_back_manage_address:
			finish();
			break;
		case R.id.tv_add_address_manage_address:
			Intent i = new Intent(this,NewAddressActivity.class);
			startActivityForResult(i,1);
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Address address = (Address) addresses.get(position);
		Intent data = new Intent();
		data.putExtra("address", address);
		this.setResult(1, data);
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			Address address = (Address) data.getExtras().get("address");
			try {
				if (addresses.size() == 0){
					address.setDefault(1);
				}
				adapter.addAddress(address);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	private void initViews() {
		tvBack = (TextView) findViewById(R.id.tv_back_manage_address);
		lvAddress = (ListView) findViewById(R.id.lv_manage_address);
		tvAdd = (TextView) findViewById(R.id.tv_add_address_manage_address);
		addresses = new ArrayList<Address>();
	}
}
