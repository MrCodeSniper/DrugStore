package com.example.android.chaoshi.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	
	private static Toast toast;

	private ToastUtils() {
	}

	public static void makeText(Context context, CharSequence text) {
		if (toast==null) {
			toast=Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}
}
