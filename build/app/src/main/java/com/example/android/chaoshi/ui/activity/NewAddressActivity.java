package com.example.android.chaoshi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.Address;


public class NewAddressActivity extends Activity implements OnClickListener {
	private TextView tvBack;
	private TextView tvSave;
	private EditText etName;
	private EditText etPhoneNumber;
	private EditText etAddress;
	private Address address;
	private String username;
	private String name;
	private String phoneNumber;
	private String addrezz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_address);

		initViews();

		tvBack.setOnClickListener(this);
		tvSave.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_back_new_address:
			finish();
			break;
		case R.id.tv_save_new_address:
			setAddress();
			if (name != null && !"".equals(name) ) {
				if (phoneNumber != null && !"".equals(phoneNumber)) {
					if (addrezz != null && !"".equals(addrezz)) {
						Intent data = new Intent();
						data.putExtra("address", address);
						this.setResult(1,data);
						finish();
					}else {
						Toast.makeText(this, "请输入收件人地址ַ", Toast.LENGTH_SHORT).show();
					}
				}else {
					Toast.makeText(this, "请输入收件人电话", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(this, "请输入收件人姓名", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	private void setAddress() {
		address = new Address();
		name = etName.getText().toString().trim();
		phoneNumber = etPhoneNumber.getText().toString().trim();
		addrezz = etAddress.getText().toString().trim();
		address.setName(name);
		address.setPhoneNumber(phoneNumber);
		address.setAddrezz(addrezz);
		address.setDefault(0);
	}

	private void initViews() {
		tvBack = (TextView) findViewById(R.id.tv_back_new_address);
		tvSave = (TextView) findViewById(R.id.tv_save_new_address);
		etName = (EditText) findViewById(R.id.et_name_new_address);
		etPhoneNumber = (EditText) findViewById(R.id.et_phonenumber_new_address);
		etAddress = (EditText) findViewById(R.id.et_address_new_address);
	}
}
