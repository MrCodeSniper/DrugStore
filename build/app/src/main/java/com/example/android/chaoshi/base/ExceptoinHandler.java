package com.example.android.chaoshi.base;

import android.util.Log;

import com.example.android.chaoshi.application.ProjectApp;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptoinHandler {

	public static void handleException(Throwable e) {
		if (ProjectApp.isRelease) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			String errorInfo = stringWriter.toString();

			Log.i("errorInfo", errorInfo);
		} else {
			e.printStackTrace();
		}

	}
}
