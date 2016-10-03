package com.example.android.chaoshi.bean.shop;

public class Commodity {

	private String pic;
	private String name;
	private int count;
	private double price;
	
	public Commodity(String name, String pic,int count,double price) {
		this.name=name;
		this.pic=pic;
		this.count=count;
		this.price=price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
