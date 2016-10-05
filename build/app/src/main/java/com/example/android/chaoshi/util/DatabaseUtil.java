package com.example.android.chaoshi.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.chaoshi.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
	private Context context;

	public DatabaseUtil(Context context) {
		super();
		setContext(context);
	}

	public void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("参数Context不允许为null");
		}
		this.context = context;
	}

	public void insert(Address address) {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String nullColumnHack = "id";
		ContentValues values = new ContentValues();
		values.put("name", address.getName());
		values.put("phoneNumber", address.getPhoneNumber());
		values.put("addrezz", address.getAddrezz());
		values.put("isDefault", address.isDefault());
		db.insert("address", nullColumnHack, values);
		db.close();
	}

	public List<Address> query(String whereClause, String[] whereArgs) {
		List<Address> addresses = new ArrayList<Address>();

		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		String[] columns = {
				"id",
				"name",
				"phoneNumber",
				"addrezz",
				"isDefault"
		};
		String selection = whereClause; 
		String[] selectionArgs = whereArgs;
		String groupBy = null;
		String having = null;
		Cursor c = db.query("address", columns, selection, selectionArgs, groupBy, having, null);
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				Address a = new Address();
				a.setId(c.getInt(0));
				a.setName(c.getString(1));
				a.setPhoneNumber(c.getString(2));
				a.setAddrezz(c.getString(3));
				a.setDefault(c.getInt(4));
				addresses.add(a);
				c.moveToNext();
			}
		}

		c.close();
		db.close();
		Log.e("taz",addresses+":addr");
		return addresses;
	}

	public long getCaseNum( ){  
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
	    String sql = "select count(*) from address";  
	    Cursor cursor = db.rawQuery(sql, null);  
	    cursor.moveToFirst();  
	    long count = cursor.getLong(0);  
	    cursor.close();  
	    return count;  
	}  
	
	public void delete(int id) {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		String table = "address";
		String whereClause = "id=?";
		String[] whereArgs = { id + "" };
		db.delete(table, whereClause, whereArgs);
		db.close();
	}

	public void update(Address a) {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		String whereClause = "id=?";
		String[] whereArgs = { a.getId() + "" };
		ContentValues values = new ContentValues();
		values.put("name", a.getName());
		values.put("phoneNumber", a.getPhoneNumber());
		values.put("addrezz", a.getAddrezz());
		values.put("isDefault", a.isDefault());
		db.update("address", values, whereClause, whereArgs);

		db.close();
	}
}
