package com.example.android.chaoshi.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "address.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table address (id Integer primary key autoincrement not null, name varchar(10) not null, phoneNumber varchar(20) not null, addrezz varchar(150) not null, isDefault Integer not null)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
