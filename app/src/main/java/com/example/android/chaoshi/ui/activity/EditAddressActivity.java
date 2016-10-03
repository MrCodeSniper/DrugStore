package com.example.android.chaoshi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.Address;
import com.example.android.chaoshi.util.DatabaseUtil;

public class EditAddressActivity extends Activity implements OnClickListener {
	private TextView tvBack;
	private TextView tvSave;
	private EditText etName;
	private EditText etPhoneNumber;
	private EditText etAddress;
	private Address address;
	private int position;
	private static final String ACTION_EDIT_ADDRESS_COMPLETE = "cn.sakura.ACTION_EDIT_ADDRESS_COMPLETE";
	private static final String ACTION_DELETE_ADDRESS = "cn.sakura.ACTION_DELETE_ADDRESS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_address);

		initViews();
		getRecentAddress();

		setListeners();
	}

	private void setListeners() {
		tvBack.setOnClickListener(this);
		tvSave.setOnClickListener(this);
	}

	private void getRecentAddress() {
		Intent i = getIntent();
		position = i.getIntExtra("position", -1);
		
		address = ManageAddressActivity.getAddressByPosition(position);
		etName.setText(address.getName());
		etPhoneNumber.setText(address.getPhoneNumber());
		etAddress.setText(address.getAddrezz());
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_back_edit_address:
			finish();
			break;
		case R.id.tv_save_edit_address:
			setAddress();
			Intent intent = new Intent(ACTION_EDIT_ADDRESS_COMPLETE);
			
			new DatabaseUtil(this).update(address);
			
			sendBroadcast(intent);
			finish();
			break;
		}
	}

	private void setAddress() {
		String name = etName.getText().toString().trim();
		String phoneNumber = etPhoneNumber.getText().toString().trim();
		String addrezz = etAddress.getText().toString().trim();
		address.setName(name);
		address.setPhoneNumber(phoneNumber);
		address.setAddrezz(addrezz);
	}

	private void initViews() {
		tvBack = (TextView) findViewById(R.id.tv_back_edit_address);
		tvSave = (TextView) findViewById(R.id.tv_save_edit_address);
		etName = (EditText) findViewById(R.id.et_name_edit_address);
		etPhoneNumber = (EditText) findViewById(R.id.et_phonenumber_edit_address);
		etAddress = (EditText) findViewById(R.id.et_address_edit_address);
	}
}
