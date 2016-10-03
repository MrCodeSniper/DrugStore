package com.example.android.chaoshi.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.example.android.chaoshi.application.ProjectApp;
import com.example.android.chaoshi.ui.activity.MainActivity;

import java.lang.Thread.UncaughtExceptionHandler;


public class CrashHandler implements UncaughtExceptionHandler {
    ProjectApp mApp;

    public CrashHandler(ProjectApp application) {
        this.mApp = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ExceptoinHandler.handleException(ex);
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(mApp, "...", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        Intent intent = new Intent(mApp, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mApp, 100, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        AlarmManager alarmManager = (AlarmManager) mApp
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 2000,
                pendingIntent);
        try {
            Thread.currentThread().sleep(2000);
        } catch (Exception e) {
           
        }
        mApp.finish();

    }
}
