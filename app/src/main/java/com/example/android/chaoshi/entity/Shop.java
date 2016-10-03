package com.example.android.chaoshi.entity;

import android.graphics.Bitmap;

public class Shop {
	private String store;
	private Bitmap pic;
	private String name;
	private int price;
	private int count;
	public Shop(Bitmap pic, String name, int price,String store,int count) {
		super();
		this.pic = pic;
		this.store = store;
		this.name = name;
		this.price = price;
		this.count=count;
	}
	public Bitmap getPic() {
		return pic;
	}
	public void setPic(Bitmap pic) {
		this.pic = pic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return getName();
	}
}
