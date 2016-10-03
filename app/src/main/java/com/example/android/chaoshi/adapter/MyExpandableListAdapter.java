package com.example.android.chaoshi.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.chaoshi.R;
import com.example.android.chaoshi.bean.shop.Commodity;
import com.example.android.chaoshi.bean.shop.Store;
import com.example.android.chaoshi.ui.fragment.CartFragment;

import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyExpandableListAdapter extends BaseExpandableListAdapter{

	private LayoutInflater inflater;
	private HashMap<Integer, List<Commodity>> mCartData;
	private ArrayList<Store> mStoreList;
	private CartFragment cartFragment;
	private OnClick mOnClickListener;
	private HashMap<Integer, List<ChildHolder>> mChildMap;
	private OnCheckChange mOnCheckListener;
	private ArrayList<GroupHolder> mGroupList;
	private ArrayList<Commodity> mCommitList;

	public MyExpandableListAdapter(CartFragment cartFragment, ArrayList<Store> mStoreList,
			HashMap<Integer, List<Commodity>> mCartData) {
		this.cartFragment=cartFragment;
		this.mStoreList=mStoreList;
		this.mCartData=mCartData;
		inflater = LayoutInflater.from(cartFragment.getActivity());
		mOnClickListener=new OnClick();
		mChildMap=new HashMap<Integer, List<ChildHolder>>();
		mOnCheckListener=new OnCheckChange();
		mGroupList=new ArrayList<GroupHolder>();
		mCommitList=new ArrayList<Commodity>();
	}


	@Override
	public int getGroupCount() {
		if (mStoreList.size()==0) {
			cartFragment.showEmpty();
		}
		return mStoreList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		int count=0;
		if (mCartData.get(groupPosition)==null) {
		}else {
			count=mCartData.get(groupPosition).size();
		}
		return count;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mStoreList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return mCartData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupHolder groupHolder = null;
		if (convertView == null) {
			convertView =inflater.inflate(R.layout.parent_layout, null);
			groupHolder = new GroupHolder();
			groupHolder.name = (TextView) convertView.findViewById(R.id.tv_title_parent);
			groupHolder.check=(CheckBox) convertView.findViewById(R.id.cb_select_parent);
			groupHolder.check.setOnCheckedChangeListener(mOnCheckListener);
			mChildMap.put(groupPosition, new ArrayList<ChildHolder>());
			convertView.setTag(groupHolder);
			mGroupList.add(groupHolder);
		}else {
			groupHolder = (GroupHolder) convertView.getTag();
		}
		Tag tag = new Tag(groupPosition);
		groupHolder.name.setText(mStoreList.get(groupPosition).getName());
		groupHolder.check.setTag(tag);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		ChildHolder childHolder = null;
		try {
			if (convertView == null) {
                convertView = inflater.inflate(R.layout.child_layout, null);
                childHolder = new ChildHolder();
                childHolder.pic = (ImageView) convertView.findViewById(R.id.iv_logo);
                childHolder.name = (TextView) convertView.findViewById(R.id.tv_shop_name);
                childHolder.check=(CheckBox) convertView.findViewById(R.id.cb_select_child);
                childHolder.ll=(LinearLayout) convertView.findViewById(R.id.ll_editor);
                childHolder.delete=(TextView)convertView.findViewById(R.id.tv_delete);
                childHolder.count=(TextView) convertView.findViewById(R.id.tv_count);
                childHolder.countNow=(TextView) convertView.findViewById(R.id.tv_count_now);
                childHolder.price=(TextView) convertView.findViewById(R.id.tv_discount_price);
                childHolder.add=(ImageView) convertView.findViewById(R.id.iv_add);
                childHolder.reduce=(ImageView)convertView.findViewById(R.id.iv_reduce);
                List<ChildHolder> list = mChildMap.get(groupPosition);
                childHolder.check.setOnCheckedChangeListener(mOnCheckListener);
                list.add(childHolder);
                childHolder.add.setOnClickListener(mOnClickListener);
                childHolder.reduce.setOnClickListener(mOnClickListener);
                childHolder.delete.setOnClickListener(mOnClickListener);
                convertView.setTag(childHolder);
            }else {
                childHolder = (ChildHolder) convertView.getTag();
            }
			Tag tag = new Tag(groupPosition,childPosition);
			x.image().bind(childHolder.pic,mCartData.get(groupPosition).get(childPosition).getPic());
			childHolder.name.setText(mCartData.get(groupPosition).get(childPosition).getName());
			childHolder.count.setText(""+mCartData.get(groupPosition).get(childPosition).getCount());
			childHolder.price.setText(""+mCartData.get(groupPosition).get(childPosition).getPrice());
			childHolder.check.setTag(tag);
			childHolder.delete.setTag(tag);
			childHolder.add.setTag(tag);
			childHolder.reduce.setTag(tag);
		} catch (Exception e) {
			convertView = new TextView(cartFragment.getContext());
		}
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	public void checkAll(boolean ischecked){
		for (GroupHolder groupHolder : mGroupList) {
			groupHolder.check.setChecked(ischecked);
		}
	}
	public void changedAllStatus(boolean isShow) {
		for (int i = 0; i < mChildMap.size(); i++) {
			changeShowStatus(mChildMap.get(i), isShow);
		}
	}
	private void changeShowStatus(List<ChildHolder> list,boolean isShow){
		for (ChildHolder childHolder : list) {
			if (isShow) {
				childHolder.ll.setVisibility(View.VISIBLE);
			}else {
				childHolder.ll.setVisibility(View.GONE);
			}
		}
	}
	public void deleteItems(Tag tag) {
		try {
			List<Commodity> list = getCommoodityList(tag);
			list.remove(tag.childPosition);
			mChildMap.get(tag.groupPosition).remove(tag.childPosition);
			if (list.size()==0) {
                mCartData.remove(tag.groupPosition);
                mStoreList.remove(tag.groupPosition);
            }
			notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private List<Commodity> getCommoodityList(Tag tag) {
		List<Commodity> list = mCartData.get(tag.groupPosition);
		return list;
	}
	public void addCount(Tag tag) {
		Commodity commodity = getCommoodityList(tag).get(tag.childPosition);
		int count = commodity.getCount();
		count++;
		commodity.setCount(count);
		ChildHolder childHolder = getChildHolder(tag);
		Log.i("LOA", "CHILdHOLDER:"+childHolder.name.getText().toString());
		childHolder.countNow.setText(""+commodity.getCount());
		childHolder.count.setText(""+commodity.getCount());
	}
	private ChildHolder getChildHolder(Tag tag) {
		Log.i("LOA", "getChildHolder:"+tag.groupPosition+" C:"+tag.childPosition);
		for (int i = 0; i < mChildMap.size(); i++) {
			List<ChildHolder> list = mChildMap.get(i);
			int j = 0;
			for (ChildHolder childHolder : list) {
				Log.i("LOA", "FOR:CHILD.G:"+i+"-->CHILD.C:"+j+"-->CHILD.NAME:"+childHolder.name.getText().toString());
				j++;
			}
		}
		return mChildMap.get(tag.groupPosition).get(tag.childPosition);
	}
	public void reduceCount(Tag tag) {
		Commodity commodity = getCommoodityList(tag).get(tag.childPosition);
		int count = commodity.getCount();
		count--;
		commodity.setCount(count);
		ChildHolder childHolder = getChildHolder(tag);
		childHolder.countNow.setText(""+commodity.getCount());
	}
	private class OnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			Tag tag = (Tag) v.getTag();
			switch (v.getId()) {
			case R.id.tv_delete:
				deleteItems(tag);
				break;
			case R.id.iv_add:
				addCount(tag);
				break;
			case R.id.iv_reduce:
				reduceCount(tag);
				break;
			}
			Log.i("LOA", "TAG:"+tag.groupPosition+"  C:"+tag.childPosition);
		}
	}
	private class OnCheckChange implements OnCheckedChangeListener{
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			Tag tag = (Tag) buttonView.getTag();
			switch (buttonView.getId()) {
			case R.id.cb_select_parent:
				checkThisAll(tag.groupPosition,isChecked);
				break;
			case R.id.cb_select_child:
				setChildChecked(tag,isChecked);
				break;
			}
		}
	}
	public List<Commodity> getCommitList(){
		mCommitList.clear();
		for (int i = 0; i < mChildMap.size(); i++) {
			List<ChildHolder> list = mChildMap.get(i);
			for (int j = 0; j < list.size(); j++) {
				ChildHolder childHolder = list.get(j);
				if (childHolder.checkState) {
					mCommitList.add(mCartData.get(i).get(j));
				}
			}
		}
		return mCommitList;
	}
	
	public void setChildChecked(Tag tag, boolean isChecked2) {
		List<ChildHolder> list = mChildMap.get(tag.groupPosition);
		boolean ischecked=list.get(0).check.isChecked();
		for (ChildHolder childHolder : list) {
			if (ischecked==childHolder.check.isChecked()) {
				//��ͬ��������Ӧ
			}else {
				//��ͬ
				ischecked=false;
				break;
			}
		}
		mGroupList.get(tag.groupPosition).isChildSet=true;
		mGroupList.get(tag.groupPosition).check.setChecked(ischecked);
		mGroupList.get(tag.groupPosition).isChildSet=false;
		list.get(tag.childPosition).checkState=isChecked2;
		cartFragment.omDataChanged();
	}
	private void checkThisAll(int groupPosition,boolean checked) {
		if (mGroupList.get(groupPosition).isChildSet) {
			//�����״̬�仯�����ĸ���״̬�仯
			for (GroupHolder groupHolder : mGroupList) {
				if (checked==groupHolder.check.isChecked()) {
				}else {
					checked=false;
					break;
				}
			}
			CartFragment.isParentSet=true;
			cartFragment.changeCheck(checked);
			CartFragment.isParentSet=false;
		}else{
			List<ChildHolder> list = mChildMap.get(groupPosition);
			for (ChildHolder childHolder : list) {
				childHolder.check.setChecked(checked);
			}
		}
	}
	private class GroupHolder{
		public CheckBox check;
		public TextView name;
		public TextView edit;

		private boolean isChildSet;
	}
	private class ChildHolder{
		public TextView count;
		public TextView price;
		public ImageView reduce;
		public ImageView add;
		public TextView delete;
		public TextView countNow;
		public LinearLayout ll;
		public CheckBox check;
		public ImageView pic;
		public TextView name;
		public boolean isShow;
		public boolean checkState;
	}
	private class Tag{
		public Tag(int groupPosition, int childPosition) {
			this.groupPosition=groupPosition;
			this.childPosition=childPosition;
		}
		public Tag(int groupPosition) {
			this(groupPosition,-1);
		}
		private int groupPosition;
		private int childPosition;
	}
}
