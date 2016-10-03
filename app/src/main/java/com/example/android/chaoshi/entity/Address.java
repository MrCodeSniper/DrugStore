package com.example.android.chaoshi.entity;

import java.io.Serializable;

public class Address implements Serializable {
	private int id;
	private String name;
	private String phoneNumber;
	private String addrezz;
	private int isDefault;

	public Address() {
		super();
	}
	
	public Address(int id, String name, String phoneNumber,
			String addrezz, int isDefault) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.addrezz = addrezz;
		this.isDefault = isDefault;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddrezz() {
		return addrezz;
	}
	public void setAddrezz(String addrezz) {
		this.addrezz = addrezz;
	}
	public int isDefault() {
		return isDefault;
	}
	public void setDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Address [name=" + name
				+ ", phoneNumber=" + phoneNumber + ", addrezz=" + addrezz
				+ ", isDefault=" + isDefault + "]";
	}

}
