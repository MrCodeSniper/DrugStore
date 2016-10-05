package com.example.android.chaoshi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.entity.Address;
import com.example.android.chaoshi.ui.activity.EditAddressActivity;
import com.example.android.chaoshi.ui.activity.ManageAddressActivity;
import com.example.android.chaoshi.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;


public class ManageAddressAdapter extends BaseAdapter {
	private Context context;
	private List<Address> addresses;
	private Address address;
	private int radioCurrentPosition = ManageAddressActivity.getDefaultAddressPosition();

	public ManageAddressAdapter(Context context, List<Address> addresses) {
		super();
		this.context = context;
		this.addresses = addresses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		address = (Address) addresses.get(position);

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.address, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name_mode);
			holder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number_mode);
			holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address_mode);
			holder.rbSetDefault = (RadioButton) convertView.findViewById(R.id.rb_set_default_mode);
			holder.tvEdit = (TextView) convertView.findViewById(R.id.tv_edit_address_mode);
			holder.tvDelete = (TextView) convertView.findViewById(R.id.tv_delete_address_mode);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		holder.tvName.setText(address.getName());
		holder.tvNumber.setText(address.getPhoneNumber());
		holder.tvAddress.setText(address.getAddrezz());

		holder.rbSetDefault.setOnCheckedChangeListener(new RadioButtonAddressAdapterListener(position));

		if (radioCurrentPosition == position) {//��
			holder.rbSetDefault.setChecked(true);
			setDefaultState(radioCurrentPosition);
		} else {
			holder.rbSetDefault.setChecked(false);
			address.setDefault(0);
		}

		InnerOnClickListener listener = new InnerOnClickListener(position);
		holder.tvEdit.setOnClickListener(listener);
		holder.tvDelete.setOnClickListener(listener);

		return convertView;
	}

	private void setDefaultState(int a) {
		for (int i = 0; i < addresses.size(); i++) {
			if (i == a) {
				address.setDefault(1);
			}else {
				address.setDefault(0);
			}
		}
	}

	private class InnerOnClickListener implements OnClickListener {
		private int position;
		private DatabaseUtil dbUtil = new DatabaseUtil(context);

		InnerOnClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tv_edit_address_mode:
				Intent i = new Intent(context,EditAddressActivity.class);
				i.putExtra("position", position);
				context.startActivity(i);
				break;
			case R.id.tv_delete_address_mode:
				dbUtil.delete(addresses.get(position).getId());
				addresses.remove(position);
				ManageAddressActivity.count--;
				notifyDataSetChanged();
				break;
			}
		}

	}

	/**
	 * 添加地址
	 * @param a
	 */
	public void addAddress(Address a) throws Exception {
		if (addresses == null) {
			addresses = new ArrayList<Address>();
		}

		addresses.add(a);
		ManageAddressActivity.count++;

		new DatabaseUtil(context).insert(a);

		notifyDataSetChanged();
	}

	private class RadioButtonAddressAdapterListener implements CompoundButton.OnCheckedChangeListener {
		private int position;

		RadioButtonAddressAdapterListener(int position) {
			this.position = position;
		}

		public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
			if(b){
				radioCurrentPosition = position;
				DatabaseUtil dbUtil = new DatabaseUtil(context);
				for (int i = 0; i < addresses.size(); i++) {
					if (i == radioCurrentPosition) {
						addresses.get(i).setDefault(1);
					}else {
						addresses.get(i).setDefault(0);
					}
					dbUtil.update(addresses.get(i));
				}

				notifyDataSetChanged();
			}
		}
	}

	class ViewHolder {
		TextView tvName;
		TextView tvNumber;
		TextView tvAddress;
		RadioButton rbSetDefault;
		TextView tvEdit;
		TextView tvDelete;
	}

	@Override
	public int getCount() {
		return addresses.size();
	}

	@Override
	public Object getItem(int position) {
		return addresses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
