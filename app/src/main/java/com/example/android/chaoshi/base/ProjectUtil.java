package com.example.android.chaoshi.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.hardware.Camera;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.chaoshi.application.ProjectApp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * Created by Administrator on 2016/8/14/014.
 */
public final class ProjectUtil {

    public static final String APP_NAME = "小卖部";

    /**
     * 是否允许输出log
     * <p/>
     * 日志输出级别 NONE - - > L.LEVEL_NONE 日志输出级别 E - - > L.LEVEL_ERRO 日志输出级别 W - - >
     * L.LEVEL_WARN 日志输出级别 I - - > L.LEVEL_INFO 日志输出级别 D - - > L.LEVEL_DEBUG
     * 日志输出级别 V - - > L.LEVEL_VERBOSE
     */
    private static int mDebuggable = L.LEVEL_VERBOSE;

    /**
     * 获取应用名
     *
     * @return
     */
    public static String getAPP_NAME() {
        return APP_NAME;
    }

    /**
     * 获取全局上下文对象
     *
     * @return
     */
    public static Context getContext() {
        return ProjectApp.getInstance();
    }

    /**
     * 获取全局handler
     *
     * @return
     */
    public static Handler getHandler() {
        return ProjectApp.getHandler();
    }

    // /////////////////加载资源文件 ///////////////////////////

    /**
     * value文件里的字符串
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    /**
     * value文件里的字符串数组
     *
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    /**
     * getDrawable获取图片
     */
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    /**
     * getColor获取颜色
     *
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    /**
     * 根据id获取颜色的状态选择器
     *
     * @param id
     * @return
     */
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    /**
     * 获取屏幕尺寸
     *
     * @param id
     * @return 返回具体像素值
     */
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    // /////////////////dip和px转换//////////////////////////

    /**
     * 不解释
     *
     * @param dip
     * @return
     */
    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    /**
     * 获取UUID
     *
     * @return 32UUID小写字符串
     */
    public static String gainUUID() {
        String strUUID = UUID.randomUUID().toString();
        strUUID = strUUID.replaceAll("-", "").toLowerCase();
        return strUUID;
    }

    /**
     * 判断字符串是否非空非null
     *
     * @param strParm 需要判断的字符串
     * @return 真假
     */
    public static boolean isNoBlankAndNoNull(String strParm) {
        return !((strParm == null) || (strParm.equals("")));
    }

    /**
     * 将流转成字符串
     *
     * @param is 输入流
     * @return
     * @throws Exception
     */
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * 将文件转成字符串
     *
     * @param file 文件
     * @return
     * @throws Exception
     */
    public static String getStringFromFile(File file) throws Exception {
        FileInputStream fin = new FileInputStream(file);
        String ret = convertStreamToString(fin);
        // Make sure you close all streams.
        fin.close();
        return ret;
    }

    /**
     * 字符全角化
     *
     * @param input
     * @return
     */
    public static String ToSBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 不解释
     *
     * @param px
     * @return
     */
    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    /**
     * 跟句加载布局文件
     *
     * @param id
     * @return
     */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    /**
     * 将runnable对象运行在主线程
     *
     * @param r
     */
    public static void runOnUIThread(Runnable r) {
        getHandler().post(r);
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                L.e(e);
            }
        }
        return true;
    }

    /**
     * 全局sharedPreferences对象
     */
    public static final class SP {
        private static SharedPreferences sharedPreferences;
        private static Editor edit;

        private SP() {
        }

        public static boolean getBoolean(String key, boolean defult) {
            if (sharedPreferences == null) {
                initSP();
            }
            return sharedPreferences.getBoolean(key, defult);
        }

        public static void putBoolean(String key, boolean value) {
            if (sharedPreferences == null) {
                initSP();
            }
            edit.putBoolean(key, value);
            edit.commit();

        }

        public static String getString(String key, String defult) {
            if (sharedPreferences == null) {
                initSP();
            }
            return sharedPreferences.getString(key, defult);
        }

        public static void putString(String key, String value) {
            if (sharedPreferences == null) {
                initSP();
            }
            edit.putString(key, value);
            edit.commit();
        }

        public static int getInt(String key, int defult) {
            if (sharedPreferences == null) {
                initSP();
            }
            return sharedPreferences.getInt(key, defult);
        }

        public static void putInt(String key, int value) {
            if (sharedPreferences == null) {
                initSP();
            }
            edit.putInt(key, value);
            edit.commit();
        }

        private static void initSP() {
            sharedPreferences = getContext().getSharedPreferences(getAPP_NAME(), getContext().MODE_PRIVATE);
            edit = sharedPreferences.edit();
        }
    }

    public static class L {
        /**
         * 日志输出级别NONE
         */
        public static final int LEVEL_NONE = 0;
        /**
         * 日志输出级别E
         */
        public static final int LEVEL_ERROR = 1;
        /**
         * 日志输出级别W
         */
        public static final int LEVEL_WARN = 2;
        /**
         * 日志输出级别I
         */
        public static final int LEVEL_INFO = 3;
        /**
         * 日志输出级别D
         */
        public static final int LEVEL_DEBUG = 4;
        /**
         * 日志输出级别V
         */
        public static final int LEVEL_VERBOSE = 5;

        /**
         * 日志输出时的TAG
         */
        private static String mTag = "LogUtils";

        /**
         * 以级别为 d 的形式输出LOG
         */
        public static void v(String msg) {
            if (mDebuggable >= LEVEL_VERBOSE) {
                Log.v(mTag, msg);
            }
        }

        /**
         * 以级别为 d 的形式输出LOG
         */
        public static void d(String msg) {
            if (mDebuggable >= LEVEL_DEBUG) {
                Log.d(mTag, msg);
            }
        }

        /**
         * 以级别为 i 的形式输出LOG
         */
        public static void i(String msg) {
            if (mDebuggable >= LEVEL_INFO) {
                Log.i(mTag, msg);
            }
        }

        /**
         * 以级别为 w 的形式输出LOG
         */
        public static void w(String msg) {
            if (mDebuggable >= LEVEL_WARN) {
                Log.w(mTag, msg);
            }
        }

        /**
         * 以级别为 w 的形式输出Throwable
         */
        public static void w(Throwable tr) {
            if (mDebuggable >= LEVEL_WARN) {
                Log.w(mTag, "", tr);
            }
        }

        /**
         * 以级别为 w 的形式输出LOG信息和Throwable
         */
        public static void w(String msg, Throwable tr) {
            if (mDebuggable >= LEVEL_WARN && null != msg) {
                Log.w(mTag, msg, tr);
            }
        }

        /**
         * 以级别为 e 的形式输出LOG
         */
        public static void e(String msg) {
            if (mDebuggable >= LEVEL_ERROR) {
                Log.e(mTag, msg);
            }
        }

        /**
         * 以级别为 e 的形式输出Throwable
         */
        public static void e(Throwable tr) {
            if (mDebuggable >= LEVEL_ERROR) {
                Log.e(mTag, "", tr);
            }
        }

        /**
         * 以级别为 e 的形式输出LOG信息和Throwable
         */
        public static void e(String msg, Throwable tr) {
            if (mDebuggable >= LEVEL_ERROR && null != msg) {
                Log.e(mTag, msg, tr);
            }
        }
    }

    public static class T {
        private static Toast toast;

        /**
         * 能够连续弹的吐司，不会等上个吐司消失
         *
         * @param text
         */
        @SuppressLint("ShowToast")
        public static void show(String text) {
            if (toast == null) {
                toast = Toast.makeText(getContext(), text, 0);
            }
            toast.setText(text);
            toast.show();
        }
    }

    /**
     * 获取资源工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolResource {
        private static final String TAG = ToolResource.class.getName();

        private static Context mContext = getContext();

        private static Class<?> CDrawable = null;

        private static Class<?> CLayout = null;

        private static Class<?> CId = null;

        private static Class<?> CAnim = null;

        private static Class<?> CStyle = null;

        private static Class<?> CString = null;

        private static Class<?> CArray = null;

        static {
            try {
                CDrawable = Class.forName(mContext.getPackageName() + ".R$drawable");
                CLayout = Class.forName(mContext.getPackageName() + ".R$layout");
                CId = Class.forName(mContext.getPackageName() + ".R$id");
                CAnim = Class.forName(mContext.getPackageName() + ".R$anim");
                CStyle = Class.forName(mContext.getPackageName() + ".R$style");
                CString = Class.forName(mContext.getPackageName() + ".R$string");
                CArray = Class.forName(mContext.getPackageName() + ".R$array");

            } catch (ClassNotFoundException e) {
                Log.i(TAG, e.getMessage());
            }
        }

        public static int getDrawableId(String resName) {
            return getResId(CDrawable, resName);
        }

        public static int getLayoutId(String resName) {
            return getResId(CLayout, resName);
        }

        public static int getIdId(String resName) {
            return getResId(CId, resName);
        }

        public static int getAnimId(String resName) {
            return getResId(CAnim, resName);
        }

        public static int getStyleId(String resName) {
            return getResId(CStyle, resName);
        }

        public static int getStringId(String resName) {
            return getResId(CString, resName);
        }

        public static int getArrayId(String resName) {
            return getResId(CArray, resName);
        }

        private static int getResId(Class<?> resClass, String resName) {
            if (resClass == null) {
                throw new IllegalArgumentException(
                        "ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have "
                                + mContext.getPackageName() + ".R$* configured in obfuscation. field=" + resName);
            }

            try {
                Field field = resClass.getField(resName);
                return field.getInt(resName);
            } catch (Exception e) {
            }
            return -1;
        }
    }

    /**
     * 常用动画类
     *
     * @author 宋昌明
     */
    public static class ToolAnimation {
        /**
         * 给试图添加点击效果,让背景变深
         */
        public static void addTouchDrak(View view) {
            view.setOnTouchListener(VIEW_TOUCH_DARK);
        }

        /**
         * 给试图添加点击效果,让背景变暗
         */
        public static void addTouchLight(View view) {
            view.setOnTouchListener(VIEW_TOUCH_LIGHT);
        }

        /**
         * 让控件点击时，颜色变深
         */
        public static final OnTouchListener VIEW_TOUCH_DARK = new OnTouchListener() {

            public final float[] BT_SELECTED = new float[]{1, 0, 0, 0, -50, 0, 1, 0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0,
                    1, 0};
            public final float[] BT_NOT_SELECTED = new float[]{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                    1, 0};

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (v instanceof ImageView) {
                        ImageView iv = (ImageView) v;
                        iv.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
                    } else {
                        v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
                        v.setBackgroundDrawable(v.getBackground());
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (v instanceof ImageView) {
                        ImageView iv = (ImageView) v;
                        iv.setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
                    } else {
                        v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
                        v.setBackgroundDrawable(v.getBackground());
                    }
                }
                return false;
            }
        };

        /**
         * 让控件点击时，颜色变暗
         */
        public static final OnTouchListener VIEW_TOUCH_LIGHT = new OnTouchListener() {

            public final float[] BT_SELECTED = new float[]{1, 0, 0, 0, 50, 0, 1, 0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1,
                    0};
            public final float[] BT_NOT_SELECTED = new float[]{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                    1, 0};

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (v instanceof ImageView) {
                        ImageView iv = (ImageView) v;
                        iv.setDrawingCacheEnabled(true);

                        iv.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
                    } else {
                        v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
                        v.setBackgroundDrawable(v.getBackground());
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (v instanceof ImageView) {
                        ImageView iv = (ImageView) v;
                        iv.setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
                        System.out.println("变回来");
                    } else {
                        v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
                        v.setBackgroundDrawable(v.getBackground());
                    }
                }
                return false;
            }
        };
    }

    /**
     * 适配不同分辨率工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolAutoFit {

        // 遍历设置字体
        public static void changeViewSize(ViewGroup viewGroup, int screenWidth, int screenHeight) {// 传入Activity顶层Layout,屏幕宽,屏幕高
            int adjustFontSize = adjustFontSize(screenWidth, screenHeight);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View v = viewGroup.getChildAt(i);
                if (v instanceof ViewGroup) {
                    changeViewSize((ViewGroup) v, screenWidth, screenHeight);
                } else if (v instanceof Button) {// 按钮加大这个一定要放在TextView上面，因为Button也继承了TextView
                    ((Button) v).setTextSize(adjustFontSize + 2);
                } else if (v instanceof TextView) {
                    if (v.getId() == ToolResource.getIdId("title_text")) {// 顶部标题
                        ((TextView) v).setTextSize(adjustFontSize + 4);
                    } else {
                        ((TextView) v).setTextSize(adjustFontSize);
                    }
                }
            }
        }

        // 获取字体大小
        public static int adjustFontSize(int screenWidth, int screenHeight) {
            screenWidth = screenWidth > screenHeight ? screenWidth : screenHeight;
            /**
             * 1. 在视图的 onsizechanged里获取视图宽度，一般情况下默认宽度是320，所以计算一个缩放比率 rate =
             * (float) w/320 w是实际宽度 2.然后在设置字体尺寸时
             * paint.setTextSize((int)(8*rate)); 8是在分辨率宽为320 下需要设置的字体大小 实际字体大小 =
             * 默认字体大小 x rate
             */
            int rate = (int) (5 * (float) screenWidth / 320); // 我自己测试这个倍数比较适合，当然你可以测试后再修改
            return rate < 15 ? 15 : rate; // 字体太小也不好看的
        }
    }

    /**
     * 读取渠道号工具类
     *
     * @author 曾繁添
     * @version 1.0
     * @link https://github.com/GavinCT/AndroidMultiChannelBuildTool
     */
    public static class ToolChannel {

        /**
         * apk/META-INF/目录写入的渠道号key
         */
        public static String CHANNEL_KEY = "ChannelName";
        /**
         * apk/META-INF/目录写入的渠道号版本
         */
        private static final String CHANNEL_VERSION_KEY = "ChannelVersion";
        private static String mChannel;

        /**
         * 返回市场。 如果获取失败返回""
         *
         * @param context
         * @return
         */
        public static String gainChannel(Context context, String channelKey) {
            CHANNEL_KEY = channelKey;
            return gainChannel(context, channelKey, "");
        }

        /**
         * 返回市场。 如果获取失败返回defaultChannel
         *
         * @param context
         * @param defaultChannel
         * @return
         */
        public static String gainChannel(Context context, String channelKey, String defaultChannel) {
            // 内存中获取
            if (!TextUtils.isEmpty(mChannel)) {
                return mChannel;
            }
            // sp中获取
            mChannel = getChannelBySharedPreferences(context);
            if (!TextUtils.isEmpty(mChannel)) {
                return mChannel;
            }
            // 从apk中获取
            mChannel = getChannelFromApk(context, channelKey);
            if (!TextUtils.isEmpty(mChannel)) {
                // 保存sp中备用
                saveChannelBySharedPreferences(context, mChannel);
                return mChannel;
            }
            // 全部获取失败
            return defaultChannel;
        }

        /**
         * 从apk中获取版本信息
         *
         * @param context
         * @param channelKey
         * @return
         */
        private static String getChannelFromApk(Context context, String channelKey) {
            // 从apk包中获取
            ApplicationInfo appinfo = context.getApplicationInfo();
            String sourceDir = appinfo.sourceDir;
            // 默认放在meta-inf/里， 所以需要再拼接一下
            String key = "META-INF/" + channelKey;
            String ret = "";
            ZipFile zipfile = null;
            try {
                zipfile = new ZipFile(sourceDir);
                Enumeration<?> entries = zipfile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = ((ZipEntry) entries.nextElement());
                    String entryName = entry.getName();
                    if (entryName.startsWith(key)) {
                        ret = entryName;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (zipfile != null) {
                    try {
                        zipfile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String[] split = ret.split("_");
            String channel = "";
            if (split != null && split.length >= 2) {
                channel = ret.substring(split[0].length() + 1);
            }
            return channel;
        }

        /**
         * 本地保存channel & 对应版本号
         *
         * @param context
         * @param channel
         */
        private static void saveChannelBySharedPreferences(Context context, String channel) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            Editor editor = sp.edit();
            editor.putString(CHANNEL_KEY, channel);
            editor.putInt(CHANNEL_VERSION_KEY, getVersionCode(context));
            editor.commit();
        }

        /**
         * 从sp中获取channel
         *
         * @param context
         * @return 为空表示获取异常、sp中的值已经失效、sp中没有此值
         */
        private static String getChannelBySharedPreferences(Context context) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            int currentVersionCode = getVersionCode(context);
            if (currentVersionCode == -1) {
                // 获取错误
                return "";
            }
            int versionCodeSaved = sp.getInt(CHANNEL_VERSION_KEY, -1);
            if (versionCodeSaved == -1) {
                // 本地没有存储的channel对应的版本号
                // 第一次使用 或者 原先存储版本号异常
                return "";
            }
            if (currentVersionCode != versionCodeSaved) {
                return "";
            }
            return sp.getString(CHANNEL_KEY, "");
        }

        /**
         * 从包信息中获取版本号
         *
         * @param context
         * @return
         */
        private static int getVersionCode(Context context) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
            return -1;
        }
    }

    /**
     * 文件工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolFile {

        private static final String TAG = ToolFile.class.getSimpleName();

        /**
         * 检查是否已挂载SD卡镜像（是否存在SD卡）
         *
         * @return
         */
        public static boolean isMountedSDCard() {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                return true;
            } else {
                Log.w(TAG, "SDCARD is not MOUNTED !");
                return false;
            }
        }

        /**
         * 获取SD卡剩余容量（单位Byte）
         *
         * @return
         */
        public static long gainSDFreeSize() {
            if (isMountedSDCard()) {
                // 取得SD卡文件路径
                File path = Environment.getExternalStorageDirectory();
                StatFs sf = new StatFs(path.getPath());
                // 获取单个数据块的大小(Byte)
                long blockSize = sf.getBlockSize();
                // 空闲的数据块的数量
                long freeBlocks = sf.getAvailableBlocks();

                // 返回SD卡空闲大小
                return freeBlocks * blockSize; // 单位Byte
            } else {
                return 0;
            }
        }

        /**
         * 获取SD卡总容量（单位Byte）
         *
         * @return
         */
        public static long gainSDAllSize() {
            if (isMountedSDCard()) {
                // 取得SD卡文件路径
                File path = Environment.getExternalStorageDirectory();
                StatFs sf = new StatFs(path.getPath());
                // 获取单个数据块的大小(Byte)
                long blockSize = sf.getBlockSize();
                // 获取所有数据块数
                long allBlocks = sf.getBlockCount();
                // 返回SD卡大小（Byte）
                return allBlocks * blockSize;
            } else {
                return 0;
            }
        }

        /**
         * 获取可用的SD卡路径（若SD卡不没有挂载则返回""）
         *
         * @return
         */
        public static String gainSDCardPath() {
            if (isMountedSDCard()) {
                File sdcardDir = Environment.getExternalStorageDirectory();
                if (!sdcardDir.canWrite()) {
                    Log.w(TAG, "SDCARD can not write !");
                }
                return sdcardDir.getPath();
            }
            return "";
        }

        /**
         * 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
         *
         * @param filePath 文件路径
         */
        public static String readFileByLines(String filePath) throws IOException {
            BufferedReader reader = null;
            StringBuffer sb = new StringBuffer();
            try {
                reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(filePath), System.getProperty("file.encoding")));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    sb.append(tempString);
                    sb.append("\n");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }

            return sb.toString();

        }

        /**
         * 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
         *
         * @param filePath 文件路径
         * @param encoding 写文件编码
         */
        public static String readFileByLines(String filePath, String encoding) throws IOException {
            BufferedReader reader = null;
            StringBuffer sb = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    sb.append(tempString);
                    sb.append("\n");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }

            return sb.toString();
        }

        /**
         * 保存内容
         *
         * @param filePath 文件路径
         * @param content  保存的内容
         * @throws IOException
         */
        public static void saveToFile(String filePath, String content) throws IOException {
            saveToFile(filePath, content, System.getProperty("file.encoding"));
        }

        /**
         * 指定编码保存内容
         *
         * @param filePath 文件路径
         * @param content  保存的内容
         * @param encoding 写文件编码
         * @throws IOException
         */
        public static void saveToFile(String filePath, String content, String encoding) throws IOException {
            BufferedWriter writer = null;
            File file = new File(filePath);
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), encoding));
                writer.write(content);

            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }

        /**
         * 追加文本
         *
         * @param content 需要追加的内容
         * @param file    待追加文件源
         * @throws IOException
         */
        public static void appendToFile(String content, File file) throws IOException {
            appendToFile(content, file, System.getProperty("file.encoding"));
        }

        /**
         * 追加文本
         *
         * @param content  需要追加的内容
         * @param file     待追加文件源
         * @param encoding 文件编码
         * @throws IOException
         */
        public static void appendToFile(String content, File file, String encoding) throws IOException {
            BufferedWriter writer = null;
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), encoding));
                writer.write(content);
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }

        /**
         * 判断文件是否存在
         *
         * @param filePath 文件路径
         * @return 是否存在
         * @throws Exception
         */
        public static Boolean isExsit(String filePath) {
            Boolean flag = false;
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("判断文件失败-->" + e.getMessage());
            }

            return flag;
        }

        /**
         * 快速读取程序应用包下的文件内容
         *
         * @param context  上下文
         * @param filename 文件名称
         * @return 文件内容
         * @throws IOException
         */
        public static String read(Context context, String filename) throws IOException {
            FileInputStream inStream = context.openFileInput(filename);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            return new String(data);
        }

        /**
         * 读取指定目录文件的文件内容
         *
         * @param fileName 文件名称
         * @return 文件内容
         * @throws Exception
         */
        public static String read(String fileName) throws IOException {
            FileInputStream inStream = new FileInputStream(fileName);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            return new String(data);
        }

        /***
         * 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
         *
         * @param fileName 文件名称
         * @param encoding 文件编码
         * @return 字符串内容
         * @throws IOException
         */
        public static String read(String fileName, String encoding) throws IOException {
            BufferedReader reader = null;
            StringBuffer sb = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    sb.append(tempString);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }

            return sb.toString();
        }

        /**
         * 读取raw目录的文件内容
         *
         * @param context   内容上下文
         * @param rawFileId raw文件名id
         * @return
         */
        public static String readRawValue(Context context, int rawFileId) {
            String result = "";
            try {
                InputStream is = context.getResources().openRawResource(rawFileId);
                int len = is.available();
                byte[] buffer = new byte[len];
                is.read(buffer);
                result = new String(buffer, "UTF-8");
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * 读取assets目录的文件内容
         *
         * @param context  内容上下文
         * @param fileName 文件名称，包含扩展名
         * @return
         */
        public static String readAssetsValue(Context context, String fileName) {
            String result = "";
            try {
                InputStream is = context.getResources().getAssets().open(fileName);
                int len = is.available();
                byte[] buffer = new byte[len];
                is.read(buffer);
                result = new String(buffer, "UTF-8");
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * 读取assets目录的文件内容
         *
         * @param context  内容上下文
         * @param fileName 文件名称，包含扩展名
         * @return
         */
        public static List<String> readAssetsListValue(Context context, String fileName) {
            List<String> list = new ArrayList<String>();
            try {
                InputStream in = context.getResources().getAssets().open(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String str = null;
                while ((str = br.readLine()) != null) {
                    list.add(str);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        /**
         * 获取SharedPreferences文件内容
         *
         * @param context       上下文
         * @param fileNameNoExt 文件名称（不用带后缀名）
         * @return
         */
        public static Map<String, ?> readShrePerface(Context context, String fileNameNoExt) {
            SharedPreferences preferences = context.getSharedPreferences(fileNameNoExt, Context.MODE_PRIVATE);
            return preferences.getAll();
        }

        /**
         * 写入SharedPreferences文件内容
         *
         * @param context       上下文
         * @param fileNameNoExt 文件名称（不用带后缀名）
         * @param values        需要写入的数据Map(String,Boolean,Float,Long,Integer)
         * @return
         */
        public static void writeShrePerface(Context context, String fileNameNoExt, Map<String, ?> values) {
            try {
                SharedPreferences preferences = context.getSharedPreferences(fileNameNoExt, Context.MODE_PRIVATE);
                Editor editor = preferences.edit();
                for (Iterator iterator = values.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, ?> entry = (Map.Entry<String, ?>) iterator.next();
                    if (entry.getValue() instanceof String) {
                        editor.putString(entry.getKey(), (String) entry.getValue());
                    } else if (entry.getValue() instanceof Boolean) {
                        editor.putBoolean(entry.getKey(), (Boolean) entry.getValue());
                    } else if (entry.getValue() instanceof Float) {
                        editor.putFloat(entry.getKey(), (Float) entry.getValue());
                    } else if (entry.getValue() instanceof Long) {
                        editor.putLong(entry.getKey(), (Long) entry.getValue());
                    } else if (entry.getValue() instanceof Integer) {
                        editor.putInt(entry.getKey(), (Integer) entry.getValue());
                    }
                }
                editor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 写入应用程序包files目录下文件
         *
         * @param context  上下文
         * @param fileName 文件名称
         * @param content  文件内容
         */
        public static void write(Context context, String fileName, String content) {
            try {

                FileOutputStream outStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                outStream.write(content.getBytes());
                outStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 写入应用程序包files目录下文件
         *
         * @param context  上下文
         * @param fileName 文件名称
         * @param content  文件内容
         */
        public static void write(Context context, String fileName, byte[] content) {
            try {

                FileOutputStream outStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                outStream.write(content);
                outStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 写入应用程序包files目录下文件
         *
         * @param context  上下文
         * @param fileName 文件名称
         * @param modeType 文件写入模式（Context.MODE_PRIVATE、Context.MODE_APPEND、Context.
         *                 MODE_WORLD_READABLE、Context.MODE_WORLD_WRITEABLE）
         * @param content  文件内容
         */
        public static void write(Context context, String fileName, byte[] content, int modeType) {
            try {

                FileOutputStream outStream = context.openFileOutput(fileName, modeType);
                outStream.write(content);
                outStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 指定编码将内容写入目标文件
         *
         * @param target   目标文件
         * @param content  文件内容
         * @param encoding 写入文件编码
         * @throws Exception
         */
        public static void write(File target, String content, String encoding) throws IOException {
            BufferedWriter writer = null;
            try {
                if (!target.getParentFile().exists()) {
                    target.getParentFile().mkdirs();
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target, false), encoding));
                writer.write(content);

            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }

        /**
         * 指定目录写入文件内容
         *
         * @param filePath 文件路径+文件名
         * @param content  文件内容
         * @throws IOException
         */
        public static void write(String filePath, byte[] content) throws IOException {
            FileOutputStream fos = null;

            try {
                File file = new File(filePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(file);
                fos.write(content);
                fos.flush();
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }

        /**
         * 写入文件
         *
         * @param inputStream下载文件的字节流对象
         * @param filePath文件的存放路径(带文件名称)
         * @throws IOException
         */
        public static File write(InputStream inputStream, String filePath) throws IOException {
            OutputStream outputStream = null;
            // 在指定目录创建一个空文件并获取文件对象
            File mFile = new File(filePath);
            if (!mFile.getParentFile().exists())
                mFile.getParentFile().mkdirs();
            try {
                outputStream = new FileOutputStream(mFile);
                byte buffer[] = new byte[4 * 1024];
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, lenght);
                }
                outputStream.flush();
                return mFile;
            } catch (IOException e) {
                Log.e(TAG, "写入文件失败，原因：" + e.getMessage());
                throw e;
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }

        /**
         * 指定目录写入文件内容
         *
         * @param filePath 文件路径+文件名
         * @param content  文件内容
         * @throws IOException
         */
        public static void saveAsJPEG(Bitmap bitmap, String filePath) throws IOException {
            FileOutputStream fos = null;

            try {
                File file = new File(filePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }

        /**
         * 指定目录写入文件内容
         *
         * @param filePath 文件路径+文件名
         * @param content  文件内容
         * @throws IOException
         */
        public static void saveAsPNG(Bitmap bitmap, String filePath) throws IOException {
            FileOutputStream fos = null;

            try {
                File file = new File(filePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }

        /**
         * 序列化对象
         *
         * @param rsls     需要序列化的对象
         * @param filename 文件名
         */
        public static synchronized <T> void serializeObject(T rsls, String filename) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(rsls);
                byte[] data = baos.toByteArray();
                OutputStream os = new FileOutputStream(new File(filename));
                os.write(data);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 反序列化对象
         *
         * @param filename 文件名
         * @return
         */
        @SuppressWarnings("unchecked")
        public static synchronized <T> T deserializeObject(String filename) {
            T obj = null;
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    obj = (T) ois.readObject();
                }
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }

    }

    public static class ToolDateTime {

        /**
         * 日期格式：yyyy-MM-dd HH:mm:ss
         **/
        public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

        /**
         * 日期格式：yyyy-MM-dd HH:mm
         **/
        public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

        /**
         * 日期格式：yyyy-MM-dd
         **/
        public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

        /**
         * 日期格式：HH:mm:ss
         **/
        public static final String DF_HH_MM_SS = "HH:mm:ss";

        /**
         * 日期格式：HH:mm
         **/
        public static final String DF_HH_MM = "HH:mm";

        private final static long minute = 60 * 1000;// 1分钟
        private final static long hour = 60 * minute;// 1小时
        private final static long day = 24 * hour;// 1天
        private final static long month = 31 * day;// 月
        private final static long year = 12 * month;// 年

        /**
         * Log输出标识
         **/
        private static final String TAG = ToolDateTime.class.getSimpleName();

        /**
         * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
         *
         * @param date
         * @return
         */
        public static String formatFriendly(Date date) {
            if (date == null) {
                return null;
            }
            long diff = new Date().getTime() - date.getTime();
            long r = 0;
            if (diff > year) {
                r = (diff / year);
                return r + "年前";
            }
            if (diff > month) {
                r = (diff / month);
                return r + "个月前";
            }
            if (diff > day) {
                r = (diff / day);
                return r + "天前";
            }
            if (diff > hour) {
                r = (diff / hour);
                return r + "个小时前";
            }
            if (diff > minute) {
                r = (diff / minute);
                return r + "分钟前";
            }
            return "刚刚";
        }

        /**
         * 将日期以yyyy-MM-dd HH:mm:ss格式化
         *
         * @param dateL 日期
         * @return
         */
        public static String formatDateTime(long dateL) {
            SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
            Date date = new Date(dateL);
            return sdf.format(date);
        }

        /**
         * 将日期以yyyy-MM-dd HH:mm:ss格式化
         *
         * @param dateL 日期
         * @return
         */
        public static String formatDateTime(long dateL, String formater) {
            SimpleDateFormat sdf = new SimpleDateFormat(formater);
            return sdf.format(new Date(dateL));
        }

        /**
         * 将日期以yyyy-MM-dd HH:mm:ss格式化
         *
         * @param dateL 日期
         * @return
         */
        public static String formatDateTime(Date date, String formater) {
            SimpleDateFormat sdf = new SimpleDateFormat(formater);
            return sdf.format(date);
        }

        /**
         * 将日期字符串转成日期
         *
         * @param strDate 字符串日期
         * @return java.util.date日期类型
         */
        public static Date parseDate(String strDate) {
            DateFormat dateFormat = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
            Date returnDate = null;
            try {
                returnDate = dateFormat.parse(strDate);
            } catch (ParseException e) {
                Log.v(TAG, "parseDate failed !");

            }
            return returnDate;

        }

        /**
         * 获取系统当前日期
         *
         * @return
         */
        public static Date gainCurrentDate() {
            return new Date();
        }

        /**
         * 获取系统当前日期
         *
         * @return
         */
        public static String gainCurrentDate(String formater) {
            return formatDateTime(new Date(), formater);
        }

        /**
         * 验证日期是否比当前日期早
         *
         * @param target1 比较时间1
         * @param target2 比较时间2
         * @return true 则代表target1比target2晚或等于target2，否则比target2早
         */
        public static boolean compareDate(Date target1, Date target2) {
            boolean flag = false;
            try {
                String target1DateTime = ToolDateTime.formatDateTime(target1, DF_YYYY_MM_DD_HH_MM_SS);
                String target2DateTime = ToolDateTime.formatDateTime(target2, DF_YYYY_MM_DD_HH_MM_SS);
                if (target1DateTime.compareTo(target2DateTime) <= 0) {
                    flag = true;
                }
            } catch (Exception e1) {
                System.out.println("比较失败，原因：" + e1.getMessage());
            }
            return flag;
        }

        /**
         * 对日期进行增加操作
         *
         * @param target 需要进行运算的日期
         * @param hour   小时
         * @return
         */
        public static Date addDateTime(Date target, double hour) {
            if (null == target || hour < 0) {
                return target;
            }

            return new Date(target.getTime() + (long) (hour * 60 * 60 * 1000));
        }

        /**
         * 对日期进行相减操作
         *
         * @param target 需要进行运算的日期
         * @param hour   小时
         * @return
         */
        public static Date subDateTime(Date target, double hour) {
            if (null == target || hour < 0) {
                return target;
            }

            return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
        }

    }

    /**
     * 自定义字体工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolFont {

        private final static String TAG = ToolFont.class.getSimpleName();

        /**
         * 从assets目录创建自定义字体样式
         *
         * @param mContext     上下文
         * @param fontFileName assets目录下的字体文件名称
         * @return
         */
        public static Typeface createTypeface(Context mContext, String fontFileName) {
            Typeface type = null;
            try {
                type = Typeface.createFromAsset(mContext.getAssets(), fontFileName);
            } catch (Exception e) {
                L.e("创建字体失败，原因：" + e.getMessage());
            }
            return type;
        }

        /**
         * 给传递的目标控件设置自定义字体样式
         *
         * @param mContext 上下文
         * @param views    需要设置字体的控件
         */
        public static void applyFontStyle(Context mContext, Typeface style, TextView... views) {
            if (null == views)
                return;

            if (null != views && views.length > 0) {
                for (TextView view : views) {
                    view.setTypeface(style);
                }
            }
        }
    }

    /**
     * HttpURLConnection工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolHttpConn {

        // 含有3个线程的线程池
        private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
        private final static String TAG = "ToolHttpConnection";

        public static Map<String, Object> download(String url, String savePath) {
            Map<String, Object> result = new HashMap<String, Object>();
            HttpURLConnection connection = null;
            try {
                connection = getConnection(url);
                String contentDisposition = getHttpHeader(connection).get("Content-Disposition");
                String displayNmae = gainFileName(contentDisposition);
                String ext = displayNmae.substring(displayNmae.lastIndexOf("."), displayNmae.length());
                File file = writeFile(connection.getInputStream(), savePath + gainUUID() + ext);
                result.put("file", file);
                result.put("displayNmae", displayNmae);
                return result;
            } catch (Exception e) {
                Log.e(TAG, "下载文件失败，原因：" + e.getMessage());
            } finally {
                if (null != connection) {
                    connection.disconnect();
                }
            }
            return null;
        }

        /**
         * 获取文件名称
         *
         * @param contentDisposition 报文头信息
         * @return
         */
        private static String gainFileName(String contentDisposition) {
            String filename = "";
            try {
                filename = new String(contentDisposition.getBytes("ISO-8859-1"), "UTF-8");
            } catch (Exception e) {
                Log.e(TAG, "获取文件名称失败，原因：" + e.getMessage());
            }
            int startIndex = filename.lastIndexOf("filename=") + 9;
            filename = filename.substring(startIndex, filename.length());

            return filename;
        }

        /**
         * 写入文件
         *
         * @param inputStream下载文件的字节流对象
         * @param filePath文件的存放路径
         */
        public static File writeFile(InputStream inputStream, String filePath) {
            OutputStream outputStream = null;
            // 在指定目录创建一个空文件并获取文件对象
            File fileDir = new File(filePath);
            if (!fileDir.getParentFile().exists())
                fileDir.getParentFile().mkdirs();
            try {
                outputStream = new FileOutputStream(fileDir);
                byte buffer[] = new byte[4 * 1024];
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, lenght);
                }
                outputStream.flush();
                return fileDir;
            } catch (Exception e) {
                Log.e(TAG, "写入文件失败，原因：" + e.getMessage());
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            return null;
        }

        /**
         * 获取连接
         *
         * @param strURL 网址
         * @return
         * @throws IOException
         */
        public static HttpURLConnection getConnection(String strURL) throws IOException {
            URL url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            configConnection(conn, strURL);
            return conn;
        }

        /**
         * 获取Header信息，可以获取文件名称、文件长度等等信息
         *
         * @param conn
         * @return
         * @throws UnsupportedEncodingException
         */
        public static Map<String, String> getHttpHeader(HttpURLConnection conn) {
            Map<String, String> header = new LinkedHashMap<String, String>();
            for (int i = 0; ; i++) {
                String mine = conn.getHeaderField(i);
                if (mine == null)
                    break;
                header.put(conn.getHeaderFieldKey(i), mine);
            }
            return header;
        }

        /**
         * 配置连接
         *
         * @param conn   链接
         * @param strURL 网址
         * @return
         * @throws ProtocolException 不支持的请求方式
         */
        private static void configConnection(HttpURLConnection conn, String strURL) throws ProtocolException {
            // 设置 HttpURLConnection超时时间
            conn.setConnectTimeout(60 * 1000);
            // 设置 HttpURLConnection的请求方式
            conn.setRequestMethod("POST");
            // 设置 HttpURLConnection的接收的文件类型
            conn.setRequestProperty("Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                            + "application/x-shockwave-flash, application/xaml+xml, "
                            + "application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, "
                            + "application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            // 设置 HttpURLConnection的接收语言
            conn.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            // 指定请求uri的源资源地址
            conn.setRequestProperty("Referer", strURL);
            // 设置 HttpURLConnection的字符编码
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            // 检查浏览页面的访问者在用什么操作系统（包括版本号）浏览器（包括版本号）和用户个人偏好
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
            // 保持持久链接
            conn.setRequestProperty("Connection", "Keep-Alive");
        }

        /**
         * 回调接口
         */
        public interface HttpResponseHandler {

            public void onSucced(InputStream result);

            public void onFailure(String result);
        }
    }

    /**
     * 地理位置相关工具类（百度地图API）
     *
     * @author 曾繁添
     */
    public static class ToolLocation {

        /**
         * 判断GPS是否打开
         *
         * @return
         */
        public static boolean isGpsOPen() {
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
            boolean isGpsOkay = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGpsOkay) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 强制打开GPS
         *
         * @param context
         */
        public static void forceOpenGPS(Context context) {
            // 4.0++
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
                intent.putExtra("enabled", true);
                context.sendBroadcast(intent);

                String provider = Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
                if (!provider.contains("gps")) { // if gps is disabled
                    final Intent poke = new Intent();
                    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
                    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
                    poke.setData(Uri.parse("3"));
                    context.sendBroadcast(poke);
                }
            } else {
                Intent GPSIntent = new Intent();
                GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
                GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
                GPSIntent.setData(Uri.parse("custom:3"));
                try {
                    PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
                } catch (CanceledException e) {
                }
            }
        }
    }

    /**
     * 基于静态内部类实现的单例，保证线程安全的网络信息工具类
     * <per> 使用该工具类之前，记得在AndroidManifest.xml添加权限许可
     * <xmp> <uses-permission android:name=
     * "android.permission.ACCESS_NETWORK_STATE" /> </xmp> </per>
     * <p/>
     * 安卓判断网络状态，只需要在相应的Activity的相关方法（onCreat/onResum）调用一行代码即可
     * NetWorkUtils.getInstance(getActivity()).validateNetWork();
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolNetwork {

        public final static String NETWORK_CMNET = "CMNET";
        public final static String NETWORK_CMWAP = "CMWAP";
        public final static String NETWORK_WIFI = "WIFI";
        public final static String TAG = "ToolNetwork";
        private static NetworkInfo networkInfo = null;
        private Context mContext = null;

        private ToolNetwork() {
        }

        public static ToolNetwork getInstance() {
            return SingletonHolder.instance;
        }

        public ToolNetwork init(Context context) {
            this.mContext = context;
            return this;
        }

        /**
         * 判断网络是否可用
         *
         * @return 是/否
         */
        public boolean isAvailable() {
            ConnectivityManager manager = (ConnectivityManager) mContext.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == manager) {
                return false;
            }
            networkInfo = manager.getActiveNetworkInfo();
            if (null == networkInfo || !networkInfo.isAvailable()) {
                return false;
            }
            return true;
        }

        /**
         * 判断网络是否已连接
         *
         * @return 是/否
         */
        public boolean isConnected() {
            if (!isAvailable()) {
                return false;
            }
            if (!networkInfo.isConnected()) {
                return false;
            }
            return true;
        }

        /**
         * 检查当前环境网络是否可用，不可用跳转至开启网络界面,不设置网络强制关闭当前Activity
         */
        public void validateNetWork() {

            if (!isConnected()) {
                Builder dialogBuilder = new Builder(mContext);
                dialogBuilder.setTitle("网络设置");
                dialogBuilder.setMessage("网络不可用，是否现在设置网络？");
                dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity) mContext).startActivityForResult(new Intent(Settings.ACTION_SETTINGS), which);
                    }
                });
                dialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogBuilder.create();
                dialogBuilder.show();
            }
        }

        /**
         * 获取网络连接信息</br>
         * 无网络：</br>
         * WIFI网络：WIFI</br>
         * WAP网络：CMWAP</br>
         * NET网络：CMNET</br>
         *
         * @return
         */
        public String getNetworkType() {
            if (isConnected()) {
                int type = networkInfo.getType();
                if (ConnectivityManager.TYPE_MOBILE == type) {
                    Log.i(TAG, "networkInfo.getExtraInfo()-->" + networkInfo.getExtraInfo());
                    if (NETWORK_CMWAP.equals(networkInfo.getExtraInfo().toLowerCase())) {
                        return NETWORK_CMWAP;
                    } else {
                        return NETWORK_CMNET;
                    }
                } else if (ConnectivityManager.TYPE_WIFI == type) {
                    return NETWORK_WIFI;
                }
            }

            return "";
        }

        /**
         * 网络是否连接
         *
         * @param context
         * @return
         */
        public static boolean isNetworkConnected(Context context) {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable();
                }
            }
            return false;
        }

        /**
         * WIFI是否连接
         *
         * @param context
         * @return
         */
        public static boolean isWIFIConnected(Context context) {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (mWiFiNetworkInfo != null) {
                    return mWiFiNetworkInfo.isAvailable();
                }
            }
            return false;
        }

        /**
         * 移动网络是否打开
         *
         * @param context
         * @return
         */
        public static boolean isMobileConnected(Context context) {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mMobileNetworkInfo = mConnectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mMobileNetworkInfo != null) {
                    return mMobileNetworkInfo.isAvailable();
                }
            }
            return false;
        }

        /**
         * 获取网络连接类型 ConnectivityManager.TYPE_MOBILE
         * ConnectivityManager.TYPE_WIFI
         *
         * @param context
         * @return
         */
        public static int getConnectedType(Context context) {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                    return mNetworkInfo.getType();
                }
            }
            return -1;
        }

        private static class SingletonHolder {

            private static ToolNetwork instance = new ToolNetwork();
        }
    }

    /**
     * 手机相关操作API
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolPhone {

        /**
         * 直接呼叫指定的号码(需要
         * <uses-permission android:name="android.permission.CALL_PHONE"/>权限)
         *
         * @param mContext    上下文Context
         * @param phoneNumber 需要呼叫的手机号码
         */
        public static void callPhone(Context mContext, String phoneNumber) {
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent call = new Intent(Intent.ACTION_CALL, uri);
            mContext.startActivity(call);
        }

        /**
         * 跳转至拨号界面
         *
         * @param mContext    上下文Context
         * @param phoneNumber 需要呼叫的手机号码
         */
        public static void toCallPhoneActivity(Context mContext, String phoneNumber) {
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent call = new Intent(Intent.ACTION_DIAL, uri);
            mContext.startActivity(call);
        }

        /**
         * 直接调用短信API发送信息(设置监听发送和接收状态)
         *
         * @param strPhone      手机号码
         * @param strMsgContext 短信内容
         */
        public static void sendMessage(final Context mContext, final String strPhone, final String strMsgContext) {

            // 处理返回的发送状态
            String SENT_SMS_ACTION = "SENT_SMS_ACTION";
            Intent sentIntent = new Intent(SENT_SMS_ACTION);
            PendingIntent sendIntent = PendingIntent.getBroadcast(mContext, 0, sentIntent, 0);
            // register the Broadcast Receivers
            mContext.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context _context, Intent _intent) {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            Toast.makeText(mContext, "短信发送成功", Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            break;
                    }
                }
            }, new IntentFilter(SENT_SMS_ACTION));

            // 处理返回的接收状态
            String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
            // create the deilverIntent parameter
            Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
            PendingIntent backIntent = PendingIntent.getBroadcast(mContext, 0, deliverIntent, 0);
            mContext.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context _context, Intent _intent) {
                    Toast.makeText(mContext, strPhone + "已经成功接收", Toast.LENGTH_SHORT).show();
                }
            }, new IntentFilter(DELIVERED_SMS_ACTION));

            // 拆分短信内容（手机短信长度限制）
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> msgList = smsManager.divideMessage(strMsgContext);
            for (String text : msgList) {
                smsManager.sendTextMessage(strPhone, null, text, sendIntent, backIntent);
            }
        }

        /**
         * 跳转至发送短信界面(自动设置接收方的号码)
         *
         * @param mActivity     Activity
         * @param strPhone      手机号码
         * @param strMsgContext 短信内容
         */
        public static void toSendMessageActivity(Context mContext, String strPhone, String strMsgContext) {
            if (PhoneNumberUtils.isGlobalPhoneNumber(strPhone)) {
                Uri uri = Uri.parse("smsto:" + strPhone);
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                sendIntent.putExtra("sms_body", strMsgContext);
                mContext.startActivity(sendIntent);
            }
        }

        /**
         * 跳转至联系人选择界面
         *
         * @param mContext    上下文
         * @param requestCode 请求返回区分代码
         */
        public static void toChooseContactsList(Activity mContext, int requestCode) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            mContext.startActivityForResult(intent, requestCode);
        }

        /**
         * 获取选择的联系人的手机号码
         *
         * @param mContext   上下文
         * @param resultCode 请求返回Result状态区分代码
         * @param data       onActivityResult返回的Intent
         * @return
         */
        public static String getChoosedPhoneNumber(Activity mContext, int resultCode, Intent data) {
            // 返回结果
            String phoneResult = "";
            if (Activity.RESULT_OK == resultCode) {
                Uri uri = data.getData();
                Cursor mCursor = mContext.managedQuery(uri, null, null, null, null);
                mCursor.moveToFirst();

                int phoneColumn = mCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                int phoneNum = mCursor.getInt(phoneColumn);
                if (phoneNum > 0) {
                    // 获得联系人的ID号
                    int idColumn = mCursor.getColumnIndex(ContactsContract.Contacts._ID);
                    String contactId = mCursor.getString(idColumn);
                    // 获得联系人的电话号码的cursor;
                    Cursor phones = mContext.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    if (phones.moveToFirst()) {
                        // 遍历所有的电话号码
                        for (; !phones.isAfterLast(); phones.moveToNext()) {
                            int index = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            int typeindex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                            int phone_type = phones.getInt(typeindex);
                            String phoneNumber = phones.getString(index);
                            switch (phone_type) {
                                case 2:
                                    phoneResult = phoneNumber;
                                    break;
                            }
                        }
                        if (!phones.isClosed()) {
                            phones.close();
                        }
                    }
                }
                // 关闭游标
                mCursor.close();
            }

            return phoneResult;
        }

        /**
         * 跳转至拍照程序界面
         *
         * @param mContext    上下文
         * @param requestCode 请求返回Result区分代码
         */
        public static void toCameraActivity(Activity mContext, int requestCode) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mContext.startActivityForResult(intent, requestCode);
        }

        /**
         * 跳转至相册选择界面
         *
         * @param mContext    上下文
         * @param requestCode
         */
        public static void toImagePickerActivity(Activity mContext, int requestCode) {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            mContext.startActivityForResult(intent, requestCode);
        }

        /**
         * 获得选中相册的图片
         *
         * @param mContext 上下文
         * @param data     onActivityResult返回的Intent
         * @return
         */
        public static Bitmap getChoosedImage(Activity mContext, Intent data) {
            if (data == null) {
                return null;
            }

            Bitmap bm = null;

            // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
            ContentResolver resolver = mContext.getContentResolver();

            // 此处的用于判断接收的Activity是不是你想要的那个
            try {
                Uri originalUri = data.getData(); // 获得图片的uri
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片
                // 这里开始的第二部分，获取图片的路径：
                String[] proj = {MediaStore.Images.Media.DATA};
                // 好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = mContext.managedQuery(originalUri, proj, null, null, null);
                // 按我个人理解 这个是获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                // 最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                // 不用了关闭游标
                cursor.close();
            } catch (Exception e) {
                Log.e("ToolPhone", e.getMessage());
            }

            return bm;
        }

        /**
         * 调用本地浏览器打开一个网页
         *
         * @param mContext   上下文
         * @param strSiteUrl 网页地址
         */
        public static void openWebSite(Context mContext, String strSiteUrl) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strSiteUrl));
            mContext.startActivity(webIntent);
        }

        /**
         * 跳转至系统设置界面
         *
         * @param mContext 上下文
         */
        public static void toSettingActivity(Context mContext) {
            Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
            mContext.startActivity(settingsIntent);
        }

        /**
         * 跳转至WIFI设置界面
         *
         * @param mContext 上下文
         */
        public static void toWIFISettingActivity(Context mContext) {
            Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            mContext.startActivity(wifiSettingsIntent);
        }

        /**
         * 启动本地应用打开PDF
         *
         * @param mContext 上下文
         * @param filePath 文件路径
         */
        public static void openPDFFile(Context mContext, String filePath) {
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);
                }
            } catch (Exception e) {
                Toast.makeText(mContext, "未检测到可打开PDF相关软件", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 启动本地应用打开PDF
         *
         * @param mContext 上下文
         * @param filePath 文件路径
         */
        public static void openWordFile(Context mContext, String filePath) {
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(path, "application/msword");
                    mContext.startActivity(intent);
                }
            } catch (Exception e) {
                Toast.makeText(mContext, "未检测到可打开Word文档相关软件", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 调用WPS打开office文档 http://bbs.wps.cn/thread-22349340-1-1.html
         *
         * @param mContext 上下文
         * @param filePath 文件路径
         */
        public static void openOfficeByWPS(Context mContext, String filePath) {

            try {

                // 文件存在性检查
                File file = new File(filePath);
                if (!file.exists()) {
                    Toast.makeText(mContext, filePath + "文件路径不存在", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 检查是否安装WPS
                String wpsPackageEng = "cn.wps.moffice_eng";// 普通版与英文版一样
                // String wpsActivity =
                // "cn.wps.moffice.documentmanager.PreStartActivity";
                String wpsActivity2 = "cn.wps.moffice.documentmanager.PreStartActivity2";// 默认第三方程序启动

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setClassName(wpsPackageEng, wpsActivity2);

                Uri uri = Uri.fromFile(new File(filePath));
                intent.setData(uri);
                mContext.startActivity(intent);

            } catch (ActivityNotFoundException e) {
                Toast.makeText(mContext, "本地未安装WPS", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(mContext, "打开文档失败", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 判断是否安装指定包名的APP
         *
         * @param mContext    上下文
         * @param packageName 包路径
         * @return
         */
        public static boolean isInstalledApp(Context mContext, String packageName) {
            if (packageName == null || "".equals(packageName)) {
                return false;
            }

            try {
                ApplicationInfo info = mContext.getPackageManager().getApplicationInfo(packageName,
                        PackageManager.GET_UNINSTALLED_PACKAGES);
                return true;
            } catch (NameNotFoundException e) {
                return false;
            }
        }

        /**
         * 判断是否存在指定的Activity
         *
         * @param mContext    上下文
         * @param packageName 包名
         * @param className   activity全路径类名
         * @return
         */
        public static boolean isExistActivity(Context mContext, String packageName, String className) {

            Boolean result = true;
            Intent intent = new Intent();
            intent.setClassName(packageName, className);

            if (mContext.getPackageManager().resolveActivity(intent, 0) == null) {
                result = false;
            } else if (intent.resolveActivity(mContext.getPackageManager()) == null) {
                result = false;
            } else {
                List<ResolveInfo> list = mContext.getPackageManager().queryIntentActivities(intent, 0);
                if (list.size() == 0) {
                    result = false;
                }
            }

            return result;
        }

    }

    /**
     * 运行环境信息
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static final class SysEnv {

        /***
         * Log输出标识
         **/
        private static final String TAG = SysEnv.class.getSimpleName();

        /***
         * 屏幕显示材质
         **/
        private static final DisplayMetrics mDisplayMetrics = new DisplayMetrics();

        /**
         * 上下文
         **/
        private static final Context context = getContext();

        /**
         * 操作系统名称(GT-I9100G)
         ***/
        public static final String MODEL_NUMBER = Build.MODEL;

        /**
         * 操作系统名称(I9100G)
         ***/
        public static final String DISPLAY_NAME = Build.DISPLAY;

        /**
         * 操作系统版本(4.4)
         ***/
        public static final String OS_VERSION = Build.VERSION.RELEASE;
        ;

        /**
         * 应用程序版本
         ***/
        public static final String APP_VERSION = getVersionName();

        /***
         * 屏幕宽度
         **/
        public static final int SCREEN_WIDTH = getDisplayMetrics().widthPixels;

        /***
         * 屏幕高度
         **/
        public static final int SCREEN_HEIGHT = getDisplayMetrics().heightPixels;

        /***
         * 本机手机号码
         **/
        public static final String PHONE_NUMBER = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();

        /***
         * 设备ID
         **/
        public static final String DEVICE_ID = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();

        /***
         * 设备IMEI号码
         **/
        public static final String IMEI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getSimSerialNumber();

        /***
         * 设备IMSI号码
         **/
        public static final String IMSI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getSubscriberId();

        /***
         * Activity之间数据传输数据对象Key
         **/
        public static final String ACTIVITY_DTO_KEY = "ACTIVITY_DTO_KEY";

        /**
         * 获取系统显示材质
         ***/
        public static DisplayMetrics getDisplayMetrics() {
            WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowMgr.getDefaultDisplay().getMetrics(mDisplayMetrics);
            return mDisplayMetrics;
        }

        /**
         * 获取摄像头支持的分辨率
         ***/
        public static List<Camera.Size> getSupportedPreviewSizes(Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
            return sizeList;
        }

        /**
         * 获取应用程序版本（versionName）
         *
         * @return 当前应用的版本号
         */
        public static String getVersionName() {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = null;
            try {
                info = manager.getPackageInfo(context.getPackageName(), 0);
            } catch (NameNotFoundException e) {
                Log.e(TAG, "获取应用程序版本失败，原因：" + e.getMessage());
                return "";
            }

            return info.versionName;
        }

        /**
         * 获取应用程序版本（versionName）
         *
         * @return 当前应用的版本号
         */
        public static int getVersionCode() {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = null;
            try {
                info = manager.getPackageInfo(context.getPackageName(), 0);
            } catch (NameNotFoundException e) {
                Log.e(TAG, "获取应用程序版本失败，原因：" + e.getMessage());
                return -1;
            }

            return info.versionCode;
        }

        /**
         * 获取系统内核版本
         *
         * @return
         */
        public static String getKernelVersion() {
            String strVersion = "";
            FileReader mFileReader = null;
            BufferedReader mBufferedReader = null;
            try {
                mFileReader = new FileReader("/proc/version");
                mBufferedReader = new BufferedReader(mFileReader, 8192);
                String str2 = mBufferedReader.readLine();
                strVersion = str2.split("\\s+")[2];// KernelVersion

            } catch (Exception e) {
                Log.e(TAG, "获取系统内核版本失败，原因：" + e.getMessage());
            } finally {
                try {
                    mBufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return strVersion;
        }

        /***
         * 获取MAC地址
         *
         * @return
         */
        public static String getMacAddress() {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo.getMacAddress() != null) {
                return wifiInfo.getMacAddress();
            } else {
                return "";
            }
        }

        /**
         * 获取运行时间
         *
         * @return 运行时间(单位/s)
         */
        public static long getRunTimes() {
            long ut = SystemClock.elapsedRealtime() / 1000;
            if (ut == 0) {
                ut = 1;
            }
            return ut;
        }

        /**
         * 判断是否为模拟器环境需要权限
         * <uses-permission android:name="android.permission.READ_PHONE_STATE"
         * />
         *
         * @param mContext 上下文
         * @return
         */
        public static boolean isEmulator(Context mContext) {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceID = telephonyManager.getDeviceId();
            // 如果 运行的 是一个 模拟器
            if (deviceID == null || deviceID.trim().length() == 0 || deviceID.matches("0+")) {
                return true;
            }
            return false;
        }
    }

    /**
     * 图片工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class ToolPicture {


        /**
         * Java代码实现高斯模糊效果，效率差
         *
         * @param sentBitmap
         * @param radius
         * @param canReuseInBitmap
         * @return
         */
        public static Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
            // Stack Blur v1.0 from
            // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
            //
            // Java Author: Mario Klingemann <mario at quasimondo.com>
            // http://incubator.quasimondo.com
            // created Feburary 29, 2004
            // Android port : Yahel Bouaziz <yahel at kayenko.com>
            // http://www.kayenko.com
            // ported april 5th, 2012

            // This is a compromise between Gaussian Blur and Box blur
            // It creates much better looking blurs than Box Blur, but is
            // 7x faster than my Gaussian Blur implementation.
            //
            // I called it Stack Blur because this describes best how this
            // filter works internally: it creates a kind of moving stack
            // of colors whilst scanning through the image. Thereby it
            // just has to add one new block of color to the right side
            // of the stack and remove the leftmost color. The remaining
            // colors on the topmost layer of the stack are either added on
            // or reduced by one, depending on if they are on the right or
            // on the left side of the stack.
            //
            // If you are using this algorithm in your code please add
            // the following line:
            //
            // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

            Bitmap bitmap;
            if (canReuseInBitmap) {
                bitmap = sentBitmap;
            } else {
                bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            }

            if (radius < 1) {
                return (null);
            }

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            int[] pix = new int[w * h];
            bitmap.getPixels(pix, 0, w, 0, 0, w, h);

            int wm = w - 1;
            int hm = h - 1;
            int wh = w * h;
            int div = radius + radius + 1;

            int r[] = new int[wh];
            int g[] = new int[wh];
            int b[] = new int[wh];
            int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
            int vmin[] = new int[Math.max(w, h)];

            int divsum = (div + 1) >> 1;
            divsum *= divsum;
            int dv[] = new int[256 * divsum];
            for (i = 0; i < 256 * divsum; i++) {
                dv[i] = (i / divsum);
            }

            yw = yi = 0;

            int[][] stack = new int[div][3];
            int stackpointer;
            int stackstart;
            int[] sir;
            int rbs;
            int r1 = radius + 1;
            int routsum, goutsum, boutsum;
            int rinsum, ginsum, binsum;

            for (y = 0; y < h; y++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                for (i = -radius; i <= radius; i++) {
                    p = pix[yi + Math.min(wm, Math.max(i, 0))];
                    sir = stack[i + radius];
                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);
                    rbs = r1 - Math.abs(i);
                    rsum += sir[0] * rbs;
                    gsum += sir[1] * rbs;
                    bsum += sir[2] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                }
                stackpointer = radius;

                for (x = 0; x < w; x++) {

                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                    }
                    p = pix[yw + vmin[x]];

                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[(stackpointer) % div];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi++;
                }
                yw += w;
            }
            for (x = 0; x < w; x++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                yp = -radius * w;
                for (i = -radius; i <= radius; i++) {
                    yi = Math.max(0, yp) + x;

                    sir = stack[i + radius];

                    sir[0] = r[yi];
                    sir[1] = g[yi];
                    sir[2] = b[yi];

                    rbs = r1 - Math.abs(i);

                    rsum += r[yi] * rbs;
                    gsum += g[yi] * rbs;
                    bsum += b[yi] * rbs;

                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }

                    if (i < hm) {
                        yp += w;
                    }
                }
                yi = x;
                stackpointer = radius;
                for (y = 0; y < h; y++) {
                    // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                    pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * w;
                    }
                    p = x + vmin[y];

                    sir[0] = r[p];
                    sir[1] = g[p];
                    sir[2] = b[p];

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi += w;
                }
            }

            bitmap.setPixels(pix, 0, w, 0, 0, w, h);
            return (bitmap);
        }

        public static Bitmap takeScreenShotQQ(Activity activity) {
            // View是你需要截图的View
            View view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap b1 = view.getDrawingCache();

            // 获取状态栏高度
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;

            // 获取屏幕长和高
            int width = activity.getWindowManager().getDefaultDisplay().getWidth();
            int height = activity.getWindowManager().getDefaultDisplay().getHeight();
            // 去掉标题栏
            Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
            if (b1 != null && !b1.isRecycled()) {
                b1.recycle();
            }
            view.destroyDrawingCache();
            Bitmap bm = scaleBitmap2DefinedSize(b);
            if (b != null && !b.isRecycled()) {
                b.recycle();
            }
            return bm;
        }

        private static Bitmap scaleBitmap2DefinedSize(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            Matrix matrix = new Matrix();
            int minLen = getMinlen(bitmap.getWidth(), bitmap.getHeight());
            float fScale = (float) SysEnv.SCREEN_WIDTH / (float) minLen;
            matrix.postScale(fScale, fScale);
            final Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                    true);
            return scaledBitmap;
        }

        private static int getMinlen(int width, int height) {
            if (width < height) {
                return width;
            } else {
                return height;
            }
        }

        /**
         * 截取应用程序界面（去除状态栏）
         *
         * @param activity 界面Activity
         * @return Bitmap对象
         */
        public static Bitmap takeScreenShot(Activity activity) {
            View view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;

            Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, statusBarHeight, SysEnv.SCREEN_WIDTH,
                    SysEnv.SCREEN_HEIGHT - statusBarHeight);
            view.destroyDrawingCache();
            return bitmap2;
        }

        /**
         * 截取应用程序界面
         *
         * @param activity 界面Activity
         * @return Bitmap对象
         */
        public static Bitmap takeFullScreenShot(Activity activity) {

            activity.getWindow().getDecorView().setDrawingCacheEnabled(true);

            Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();

            View view = activity.getWindow().getDecorView();
            Bitmap bmp2 = Bitmap.createBitmap(480, 800, Config.ARGB_8888);
            // view.draw(new Canvas(bmp2));
            // bmp就是截取的图片了，可通过bmp.compress(CompressFormat.PNG, 100, new
            // FileOutputStream(file));把图片保存为文件。

            // 1、得到状态栏高度
            Rect rect = new Rect();
            view.getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            System.out.println("状态栏高度：" + statusBarHeight);

            // 2、得到标题栏高度
            int wintop = activity.getWindow().findViewById(android.R.id.content).getTop();
            int titleBarHeight = wintop - statusBarHeight;
            System.out.println("标题栏高度:" + titleBarHeight);

            // //把两个bitmap合到一起
            // Bitmap bmpall =
            // Biatmap.createBitmap(width,height,Config.ARGB_8888);
            // Canvas canvas=new Canvas(bmpall);
            // canvas.drawBitmap(bmp1,x,y,paint);
            // canvas.drawBitmap(bmp2,x,y,paint);

            return bmp;
        }

        /**
         * 截取View内容，返回bitmap
         *
         * @param mView 需要截屏的目标View
         * @return
         */
        public static Bitmap takeViewScreenShot(View mView) {
            Bitmap result = null;
            try {
                mView.setDrawingCacheEnabled(true);
                result = mView.getDrawingCache();
                mView.setDrawingCacheEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * 截取指定View内容，保存到指定的文件目录
         *
         * @param mView    截屏目标View
         * @param filePath jpg文件路径+文件名
         * @return
         */
        public static File takeViewScreenShot(View mView, String filePath) {
            return takeViewScreenShot(mView, filePath, 80);
        }

        /**
         * 截取指定View内容，保存到指定的文件目录
         *
         * @param mView    截屏目标View
         * @param filePath jpg文件路径+文件名
         * @param quality  0-100压缩率
         * @return
         */
        public static File takeViewScreenShot(View mView, String filePath, int quality) {
            File myCaptureFile = new File(filePath);
            mView.setDrawingCacheEnabled(true);
            try {

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                mView.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, quality, bos);
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mView.setDrawingCacheEnabled(false);
            return myCaptureFile;
        }

        /**
         * 获取View内容，转成bitmap
         *
         * @param v 目标View
         * @return
         */
        public static Bitmap loadBitmapFromView(View v) {
            if (v == null) {
                return null;
            }
            Bitmap screenshot;
            screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Config.ARGB_8888);
            Canvas c = new Canvas(screenshot);
            c.translate(-v.getScrollX(), -v.getScrollY());
            v.draw(c);
            return screenshot;
        }

        /**
         * 将View转成Bitmap
         *
         * @param view 目标View
         * @return
         */
        public static Bitmap gainViewBitmap(View view) {
            view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();

            return bitmap;
        }



        /**
         * 读取图片属性：旋转的角度
         *
         * @param path 图片绝对路径
         * @return degree 旋转的角度
         * @throws IOException
         */
        public static int gainPictureDegree(String path) throws Exception {
            int degree = 0;
            try {
                ExifInterface exifInterface = new ExifInterface(path);
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            } catch (Exception e) {
                throw (e);
            }

            return degree;
        }

        /**
         * 旋转图片
         *
         * @param angle  角度
         * @param bitmap 源bitmap
         * @return Bitmap 旋转角度之后的bitmap
         */
        public static Bitmap rotaingBitmap(int angle, Bitmap bitmap) {
            // 旋转图片 动作
            Matrix matrix = new Matrix();
            ;
            matrix.postRotate(angle);
            // 重新构建Bitmap
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                    true);
            return resizedBitmap;
        }

        /**
         * Drawable转成Bitmap
         *
         * @param drawable
         * @return
         */
        public static Bitmap drawableToBitmap(Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            } else if (drawable instanceof NinePatchDrawable) {
                Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.draw(canvas);
                return bitmap;
            } else {
                return null;
            }
        }

        /**
         * 从资源文件中获取图片
         *
         * @param context    上下文
         * @param drawableId 资源文件id
         * @return
         */
        public static Bitmap gainBitmap(int drawableId) {
            Bitmap bmp = BitmapFactory.decodeResource(getContext().getResources(), drawableId);
            return bmp;
        }

        /**
         * 灰白图片（去色）
         *
         * @param bitmap 需要灰度的图片
         * @return 去色之后的图片
         */
        public static Bitmap toBlack(Bitmap bitmap) {
            Bitmap resultBMP = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.RGB_565);
            Canvas c = new Canvas(resultBMP);
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
            paint.setColorFilter(f);
            c.drawBitmap(bitmap, 0, 0, paint);
            return resultBMP;
        }

        /**
         * 将bitmap转成 byte数组
         *
         * @param bitmap
         * @return
         */
        public static byte[] toBtyeArray(Bitmap bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }

        /**
         * 将byte数组转成 bitmap
         *
         * @param b
         * @return
         */
        public static Bitmap bytesToBimap(byte[] b) {
            if (b.length != 0) {
                return BitmapFactory.decodeByteArray(b, 0, b.length);
            } else {
                return null;
            }
        }

        /**
         * 将Bitmap转换成指定大小
         *
         * @param bitmap 需要改变大小的图片
         * @param width  宽
         * @param height 高
         * @return
         */
        public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {
            return Bitmap.createScaledBitmap(bitmap, width, height, true);
        }

        /**
         * 在图片右下角添加水印
         *
         * @param srcBMP  原图
         * @param markBMP 水印图片
         * @return 合成水印后的图片
         */
        public static Bitmap composeWatermark(Bitmap srcBMP, Bitmap markBMP) {
            if (srcBMP == null) {
                return null;
            }

            // 创建一个新的和SRC长度宽度一样的位图
            Bitmap newb = Bitmap.createBitmap(srcBMP.getWidth(), srcBMP.getHeight(), Config.ARGB_8888);
            Canvas cv = new Canvas(newb);
            // 在 0，0坐标开始画入原图
            cv.drawBitmap(srcBMP, 0, 0, null);
            // 在原图的右下角画入水印
            cv.drawBitmap(markBMP, srcBMP.getWidth() - markBMP.getWidth() + 5,
                    srcBMP.getHeight() - markBMP.getHeight() + 5, null);
            // 保存
            cv.save(Canvas.ALL_SAVE_FLAG);
            // 存储
            cv.restore();

            return newb;
        }

        /**
         * 将图片转成指定弧度（角度）的图片
         *
         * @param bitmap 需要修改的图片
         * @param pixels 圆角的弧度
         * @return 圆角图片
         */
        public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            // 根据图片创建画布
            Canvas canvas = new Canvas(output);
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            final float roundPx = pixels;
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(0xff424242);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        }

        /**
         * 缩放图片
         *
         * @param bmp  需要缩放的图片源
         * @param newW 需要缩放成的图片宽度
         * @param newH 需要缩放成的图片高度
         * @return 缩放后的图片
         */
        public static Bitmap zoom(Bitmap bmp, int newW, int newH) {

            // 获得图片的宽高
            int width = bmp.getWidth();
            int height = bmp.getHeight();

            // 计算缩放比例
            float scaleWidth = ((float) newW) / width;
            float scaleHeight = ((float) newH) / height;

            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            // 得到新的图片
            Bitmap newbm = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);

            return newbm;
        }

        /**
         * 获得倒影的图片
         *
         * @param bitmap 原始图片
         * @return 带倒影的图片
         */
        public static Bitmap makeReflectionImage(Bitmap bitmap) {
            final int reflectionGap = 4;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);

            Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);
            Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);

            Paint deafalutPaint = new Paint();
            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
            canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                    bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);

            return bitmapWithReflection;
        }

        /**
         * 获取验证码图片
         *
         * @param width  验证码宽度
         * @param height 验证码高度
         * @return 验证码Bitmap对象
         */
        public synchronized static Bitmap makeValidateCode(int width, int height) {
            return ValidateCodeGenerator.createBitmap(width, height);
        }

        /**
         * 获取验证码值
         *
         * @return 验证码字符串
         */
        public synchronized static String gainValidateCodeValue() {
            return ValidateCodeGenerator.getCode();
        }

        /**
         * 随机生成验证码内部类
         */
        final static class ValidateCodeGenerator {
            private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                    'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

            // default settings
            private static final int DEFAULT_CODE_LENGTH = 4;
            private static final int DEFAULT_FONT_SIZE = 20;
            private static final int DEFAULT_LINE_NUMBER = 3;
            private static final int BASE_PADDING_LEFT = 5, RANGE_PADDING_LEFT = 10, BASE_PADDING_TOP = 15,
                    RANGE_PADDING_TOP = 10;
            private static final int DEFAULT_WIDTH = 60, DEFAULT_HEIGHT = 30;

            // variables
            private static String value;
            private static int padding_left, padding_top;
            private static Random random = new Random();

            public static Bitmap createBitmap(int width, int height) {
                padding_left = 0;
                // 创建画布
                Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                Canvas c = new Canvas(bp);

                // 随机生成验证码字符
                StringBuilder buffer = new StringBuilder();
                for (int i = 0; i < DEFAULT_CODE_LENGTH; i++) {
                    buffer.append(CHARS[random.nextInt(CHARS.length)]);
                }
                value = buffer.toString();

                // 设置颜色
                c.drawColor(Color.WHITE);

                // 设置画笔大小
                Paint paint = new Paint();
                paint.setTextSize(DEFAULT_FONT_SIZE);
                for (int i = 0; i < value.length(); i++) {
                    // 随机样式
                    randomTextStyle(paint);
                    padding_left += BASE_PADDING_LEFT + random.nextInt(RANGE_PADDING_LEFT);
                    padding_top = BASE_PADDING_TOP + random.nextInt(RANGE_PADDING_TOP);
                    c.drawText(value.charAt(i) + "", padding_left, padding_top, paint);
                }
                for (int i = 0; i < DEFAULT_LINE_NUMBER; i++) {
                    drawLine(c, paint);
                }
                // 保存
                c.save(Canvas.ALL_SAVE_FLAG);
                c.restore();

                return bp;
            }

            public static String getCode() {
                return value;
            }

            private static void randomTextStyle(Paint paint) {
                int color = randomColor(1);
                paint.setColor(color);
                paint.setFakeBoldText(random.nextBoolean());// true为粗体，false为非粗体
                float skewX = random.nextInt(11) / 10;
                skewX = random.nextBoolean() ? skewX : -skewX;
                paint.setTextSkewX(skewX); // float类型参数，负数表示右斜，整数左斜
                paint.setUnderlineText(true); // true为下划线，false为非下划线
                paint.setStrikeThruText(true); // true为删除线，false为非删除线
            }

            private static void drawLine(Canvas canvas, Paint paint) {
                int color = randomColor(1);
                int startX = random.nextInt(DEFAULT_WIDTH);
                int startY = random.nextInt(DEFAULT_HEIGHT);
                int stopX = random.nextInt(DEFAULT_WIDTH);
                int stopY = random.nextInt(DEFAULT_HEIGHT);
                paint.setStrokeWidth(1);
                paint.setColor(color);
                canvas.drawLine(startX, startY, stopX, stopY, paint);
            }

            private static int randomColor(int rate) {
                int red = random.nextInt(256) / rate;
                int green = random.nextInt(256) / rate;
                int blue = random.nextInt(256) / rate;
                return Color.rgb(red, green, blue);
            }
        }
    }

    

    /**
     * 配置文件工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static final class ToolProperties extends Properties {

        private static Properties property = new Properties();

        public static String readAssetsProp(String fileName, String key) {
            String value = "";
            try {
                InputStream in = getContext().getAssets().open(fileName);
                property.load(in);
                value = property.getProperty(key);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return value;
        }

        public static String readAssetsProp(Context context, String fileName, String key) {
            String value = "";
            try {
                InputStream in = context.getAssets().open(fileName);
                property.load(in);
                value = property.getProperty(key);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return value;
        }

        public static String readAssetsProp(String fileName, String key, String defaultValue) {
            String value = "";
            try {
                InputStream in = getContext().getAssets().open(fileName);
                property.load(in);
                value = property.getProperty(key, defaultValue);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return value;
        }

        public static String readAssetsProp(Context context, String fileName, String key, String defaultValue) {
            String value = "";
            try {
                InputStream in = context.getAssets().open(fileName);
                property.load(in);
                value = property.getProperty(key, defaultValue);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return value;
        }
    }

    /**
     * 单位换算工具类
     *
     * @author 曾繁添<br>
     *         px ：像素 <br>
     *         in ：英寸<br>
     *         mm ：毫米<br>
     *         pt ：磅，1/72 英寸<br>
     *         dp ：一个基于density的抽象单位，如果一个160dpi的屏幕，1dp=1px<br>
     *         dip ：等同于dp<br>
     *         sp ：同dp相似，但还会根据用户的字体大小偏好来缩放。<br>
     *         建议使用sp作为文本的单位，其它用dip<br>
     *         布局时尽量使用单位dip，少使用px <br>
     */
    public static class ToolUnit {

        /**
         * 获取当前分辨率下指定单位对应的像素大小（根据设备信息） px,dip,sp -> px
         * <p/>
         * Paint.setTextSize()单位为px
         * <p/>
         * 代码摘自：TextView.setTextSize()
         *
         * @param unit TypedValue.COMPLEX_UNIT_*
         * @param size
         * @return
         */
        public static float getRawSize(Context mContext, int unit, float size) {
            Resources r;
            if (mContext == null)
                r = Resources.getSystem();
            else
                r = mContext.getResources();

            return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
        }

        /**
         * 设备显示材质
         **/
        private static DisplayMetrics mDisplayMetrics = SysEnv.getDisplayMetrics();

        /**
         * sp转换px
         *
         * @param spValue sp数值
         * @return px数值
         */
        public static int spTopx(float spValue) {
            return (int) (spValue * mDisplayMetrics.scaledDensity + 0.5f);
        }

        /**
         * px转换sp
         *
         * @param pxValue px数值
         * @return sp数值
         */
        public static int pxTosp(float pxValue) {
            return (int) (pxValue / mDisplayMetrics.scaledDensity + 0.5f);
        }

        /**
         * dip转换px
         *
         * @param dipValue dip数值
         * @return px数值
         */
        public static int dipTopx(int dipValue) {
            return (int) (dipValue * mDisplayMetrics.density + 0.5f);
        }

        /**
         * px转换dip
         *
         * @param pxValue px数值
         * @return dip数值
         */
        public static int pxTodip(float pxValue) {
            return (int) (pxValue / mDisplayMetrics.density + 0.5f);
        }
    }

   
    /**
     * Base64编码
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class Base64 {

        private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
                .toCharArray();

        /**
         * data[]进行编码
         *
         * @param data
         * @return
         */
        public static String encode(byte[] data) {
            int start = 0;
            int len = data.length;
            StringBuffer buf = new StringBuffer(data.length * 3 / 2);

            int end = len - 3;
            int i = start;
            int n = 0;

            while (i <= end) {
                int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8)
                        | (((int) data[i + 2]) & 0x0ff);
                buf.append(legalChars[(d >> 18) & 63]);
                buf.append(legalChars[(d >> 12) & 63]);
                buf.append(legalChars[(d >> 6) & 63]);
                buf.append(legalChars[d & 63]);
                i += 3;
                if (n++ >= 14) {
                    n = 0;
                    buf.append(" ");
                }
            }

            if (i == start + len - 2) {
                int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);

                buf.append(legalChars[(d >> 18) & 63]);
                buf.append(legalChars[(d >> 12) & 63]);
                buf.append(legalChars[(d >> 6) & 63]);
                buf.append("=");
            } else if (i == start + len - 1) {
                int d = (((int) data[i]) & 0x0ff) << 16;

                buf.append(legalChars[(d >> 18) & 63]);
                buf.append(legalChars[(d >> 12) & 63]);
                buf.append("==");
            }

            return buf.toString();
        }

        private static int decode(char c) {
            if (c >= 'A' && c <= 'Z')
                return ((int) c) - 65;
            else if (c >= 'a' && c <= 'z')
                return ((int) c) - 97 + 26;
            else if (c >= '0' && c <= '9')
                return ((int) c) - 48 + 26 + 26;
            else
                switch (c) {
                    case '+':
                        return 62;
                    case '/':
                        return 63;
                    case '=':
                        return 0;
                    default:
                        throw new RuntimeException("unexpected code: " + c);
                }
        }

        /**
         * Decodes the given Base64 encoded String to a new byte array. The byte
         * array holding the decoded data is returned.
         */
        public static byte[] decode(String s) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                decode(s, bos);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            byte[] decodedBytes = bos.toByteArray();
            try {
                bos.close();
                bos = null;
            } catch (IOException ex) {
                System.err.println("Error while decoding BASE64: " + ex.toString());
            }
            return decodedBytes;
        }

        private static void decode(String s, OutputStream os) throws IOException {
            int i = 0;
            int len = s.length();
            while (true) {
                while (i < len && s.charAt(i) <= ' ')
                    i++;
                if (i == len)
                    break;
                int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6)
                        + (decode(s.charAt(i + 3)));
                os.write((tri >> 16) & 255);
                if (s.charAt(i + 2) == '=')
                    break;
                os.write((tri >> 8) & 255);
                if (s.charAt(i + 3) == '=')
                    break;
                os.write(tri & 255);
                i += 4;
            }
        }
    }

    /**
     * Android平台DES加解密工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    public static class DES {

        /**
         * 密钥算法名称
         **/
        private final static String ALGORITHM = "DES";

        /**
         * 请求转换 的名称：加密/解密算法/工作模式/填充方式
         **/
        private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";

        /**
         * 初始化向量(8位字节数组)
         */
        private static final byte[] bInitValue = {5, '#', 1, 'a', 0, '/', 'q', 'j'};

        /**
         * DES算法，加密
         *
         * @param data 待加密字符串
         * @param key  加密私钥，长度不能够小于8位
         * @return 加密后的字节数组，一般结合Base64编码使用
         * @throws CryptException 异常
         */
        public static String encrypt(String key, String data) throws Exception {
            return Base64.encode(encrypt(key, data.getBytes("UTF-8")));
        }

        /**
         * DES算法，加密
         *
         * @param data 待加密字符串
         * @param key  加密私钥，长度不能够小于8位
         * @return 加密后的字节数组，一般结合Base64编码使用
         * @throws CryptException 异常
         */
        public static byte[] encrypt(String key, byte[] data) throws Exception {
            try {
                // 指定算法名称构造生成SecureRandom实例
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
                // 设置密钥参数,key的长度不能够小于8位字节
                DESKeySpec dks = new DESKeySpec(key.getBytes());
                Key secretKey = keyFactory.generateSecret(dks);
                // Cipher对象实际完成加密操作
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                IvParameterSpec ips = new IvParameterSpec(bInitValue);
                // 用密匙初始化Cipher对象
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ips);

                // 执行加密操作
                byte[] bytes = cipher.doFinal(data);
                // Base64编码
                return bytes;
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

        /**
         * DES算法，解密
         *
         * @param data 待解密字符串
         * @param key  解密私钥，长度不能够小于8位
         * @return 解密后的字节数组
         * @throws Exception 异常
         */
        public static byte[] decrypt(String key, byte[] data) throws Exception {
            try {
                // 先Base64解码
                byte[] byteMi = Base64.decode(new String(data));

                // 指定算法名称构造生成SecureRandom实例
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);

                // 设置密钥参数,key的长度不能够小于8位字节
                DESKeySpec dks = new DESKeySpec(key.getBytes());
                Key secretKey = keyFactory.generateSecret(dks);

                // Cipher对象实际完成解密操作
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                IvParameterSpec ips = new IvParameterSpec(bInitValue);
                // 用密匙初始化Cipher对象
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ips);

                // 执行解密操作
                byte[] bytes = cipher.doFinal(byteMi);

                return bytes;
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

        /**
         * DES算法，解密
         *
         * @param data 待加密字符串
         * @param key  加密私钥，长度不能够小于8位
         * @return 加密后的字节数组，一般结合Base64编码使用
         * @throws CryptException 异常
         */
        public static String decrypt(String key, String data) throws Exception {
            return new String(decrypt(key, data.getBytes("UTF-8")), "UTF-8");
        }
    }

    /**
     * MD5加密工具类
     *
     * @author 曾繁添
     * @version 1.0
     */
    @SuppressLint("DefaultLocale")
    public static class MD5 {

        /**
         * 对数据进行MD5加密
         *
         * @param data 明文
         * @return
         * @throws Exception
         */
        public static String encrypt16byte(String data) {
            // 返回结果
            String result = "";
            try {

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(data.getBytes("UTF-8"));
                byte[] array = md.digest();

                StringBuffer md5StrBuff = new StringBuffer();
                for (int i = 0; i < array.length; i++) {
                    if (Integer.toHexString(0xFF & array[i]).length() == 1) {
                        md5StrBuff.append("0").append(Integer.toHexString(0xFF & array[i]));
                    } else {
                        md5StrBuff.append(Integer.toHexString(0xFF & array[i]));
                    }
                }
                // 16位加密，从第9位到25位
                result = md5StrBuff.substring(8, 24).toString().toLowerCase();
            } catch (Exception e) {

            }

            return result;
        }

        /**
         * 对数据进行MD5加密（增加长度）
         *
         * @param data 明文
         * @return
         * @throws Exception
         */
        public static String encrypt32byte(String data) throws Exception {
            byte[] hash;
            try {
                hash = MessageDigest.getInstance("MD5").digest(data.getBytes("UTF-8"));
            } catch (NoSuchAlgorithmException e) {
                throw new Exception("Huh, MD5 should be supported?", e);
            } catch (UnsupportedEncodingException e) {
                throw new Exception("Huh, UTF-8 should be supported?", e);
            }

            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10)
                    hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }

            return hex.toString().toLowerCase();
        }
    }

    /**
     * 图片加载工具类
     *
     * @author Lemon
     * @use ImageLoaderUtil.loadImage(...)
     */
    public static class ImageLoaderUtil {
        private static final String TAG = "ImageLoaderUtil";

        private static ImageLoader imageLoader;

        /**
         * 初始化方法
         *
         * @param context
         * @must 使用其它方法前必须调用，建议在自定义Application的onCreate方法中调用
         */
        public static void init(Context context) {
            if (context == null) {
                Log.e(TAG, "\n\n\n\n\n !!!!!!  <<<<<< init  context == null >> return; >>>>>>>> \n\n\n\n");
                return;
            }
            imageLoader = ImageLoader.getInstance();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                    .defaultDisplayImageOptions(getOption(0))
                    // .threadPoolSize(5)
                    // //.threadPriority(Thread.MIN_PRIORITY + 3)
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    // .discCacheSize((int)(Runtime.getRuntime().maxMemory()/2))
                    // .discCache(new UnlimitedDiscCache(getCachePath()))
                    // .memoryCacheSize(2 * 1024 * 1024)
                    // .memoryCacheExtraOptions(147, 147)
                    // .writeDebugLogs()
                    // .httpConnectTimeout(5000)
                    // .httpReadTimeout(20000)
                    .diskCacheExtraOptions(ScreenUtil.getScreenWidth(context), ScreenUtil.getScreenHeight(context),
                            null)
                    .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                    .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                    // .displayer(new RoundedBitmapDisplayer(5))
                    .build();

            imageLoader.init(config);
        }

        /**
         * 加载图片 加载小图应再调用该方法前使用getSmallUri处理uri type = TYPE_DEFAULT
         *
         * @param iv
         * @param uri 网址url或本地路径path
         */
        public static void loadImage(ImageView iv, String uri) {
            loadImage(iv, uri, TYPE_DEFAULT);
        }

        public static final int TYPE_DEFAULT = 0;
        public static final int TYPE_ROUND_CORNER = 1;
        public static final int TYPE_OVAL = 2;

        /**
         * 加载图片 加载小图应再调用该方法前使用getSmallUri处理uri
         *
         * @param type 图片显示类型
         * @param iv
         * @param uri  网址url或本地路径path
         */
        public static void loadImage(final ImageView iv, String uri, final int type) {
            if (iv == null) {// || iv.getWidth() <= 0) {
                Log.i(TAG, "loadImage  iv == null >> return;");
                return;
            }
            Log.i(TAG, "loadImage  iv" + (iv == null ? "==" : "!=") + "null; uri=" + uri);

            uri = getCorrectUri(uri);

            // 新的加载图片
            imageLoader.displayImage(uri, iv, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View arg1) {
                }

                @Override
                public void onLoadingFailed(String imageUri, View arg1, FailReason arg2) {
                }

                @Override
                public void onLoadingComplete(String imageUri, View arg1, Bitmap loadedImage) {
                    switch (type) {
                        case TYPE_OVAL:
                            iv.setImageBitmap(toRoundCorner(loadedImage, loadedImage.getWidth() / 2));
                            break;
                        case TYPE_ROUND_CORNER:
                            iv.setImageBitmap(toRoundCorner(loadedImage, 10));
                            break;
                        default:
                            iv.setImageBitmap(loadedImage);
                            break;
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View arg1) {
                }
            });

        }

        public static final String FILE_PATH_PREFIX = StringUtil.FILE_PATH_PREFIX;
        public static final String HTTP = StringUtil.HTTP;
        public static final String URL_PREFIX = StringUtil.URL_PREFIX;
        public static final String URL_PREFIXs = StringUtil.URL_PREFIXs;

        public static String URL_SUFFIX_SMALL = "!common";

        /**
         * 获取可用的uri
         *
         * @param uri
         * @return
         */
        @SuppressLint("DefaultLocale")
        public static String getCorrectUri(String uri) {
            Log.i(TAG, "<<<<  getCorrectUri  uri = " + uri);
            // if (StringUtil.isNotEmpty(uri, true) == false) {
            // Log.e(TAG, "getCorrectUri StringUtil.isNotEmpty(uri, true) ==
            // false >> return null;");
            // return null;
            // }
            uri = uri.trim();

            if (uri.toLowerCase().startsWith(HTTP)) {
            } else {
                // String path = uri.startsWith(FILE_PATH_PREFIX) ? uri :
                // FILE_PATH_PREFIX + uri;
                uri = uri.startsWith(FILE_PATH_PREFIX) ? uri : FILE_PATH_PREFIX + uri;
                // if (path.startsWith("/")) {
                // path = FILE_PATH_PREFIX + uri;
                // }
                // Log.i(TAG, "getCorrectUri uri.toLowerCase().startsWith(HTTP)
                // == false >> uri = " + uri);
                // uri = StringUtil.isFilePathExist(path) ? path : URL_PREFIX +
                // uri;
            }

            Log.i(TAG, "getCorrectUri  return uri = " + uri + " >>>>> ");
            return uri;
        }

        /**
         * 获取配置
         *
         * @param cornerRadiusSize
         * @return
         */
        private static DisplayImageOptions getOption(int cornerRadiusSize) {
            return getOption(cornerRadiusSize,
                    cornerRadiusSize <= 0 ? android.R.drawable.ic_media_play : android.R.drawable.ic_media_pause);
        }

        /**
         * 获取配置
         *
         * @param cornerRadiusSize
         * @param defaultImageResId
         * @return
         */
        @SuppressWarnings("deprecation")
        private static DisplayImageOptions getOption(int cornerRadiusSize, int defaultImageResId) {
            Options options0 = new Options();
            options0.inPreferredConfig = Config.RGB_565;

            DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
            if (defaultImageResId > 0) {
                try {
                    builder.showImageForEmptyUri(defaultImageResId).showImageOnLoading(defaultImageResId)
                            .showImageOnFail(defaultImageResId);
                } catch (Exception e) {
                    Log.e(TAG, "getOption  try {builder.showImageForEmptyUri(defaultImageResId) ..."
                            + " >> } catch (Exception e) { \n" + e.getMessage());
                }
            }
            if (cornerRadiusSize > 0) {
                builder.displayer(new RoundedBitmapDisplayer(cornerRadiusSize));
            }

            return builder.cacheInMemory(true).cacheOnDisc(true).decodingOptions(options0).build();
        }

        /**
         * 获取小图url或path path不加URL_SUFFIX_SMALL
         *
         * @param uri
         * @return
         */
        public static String getSmallUri(String uri) {
            return getSmallUri(uri, false);
        }

        /**
         * 获取小图url或path path不加URL_SUFFIX_SMALL
         *
         * @param uri
         * @param isLocalPath
         * @return
         */
        public static String getSmallUri(String uri, boolean isLocalPath) {
            if (uri == null) {
                Log.e(TAG, "getSmallUri  uri == null >> return null;");
                return null;
            }

            if (uri.startsWith("/") || uri.startsWith(FILE_PATH_PREFIX)
                    || StringUtil.isFilePathExist(FILE_PATH_PREFIX + uri)) {
                isLocalPath = true;
            }
            return isLocalPath || uri.endsWith(URL_SUFFIX_SMALL) ? uri : uri + URL_SUFFIX_SMALL;
        }

        /**
         * 将图片改为圆角类型
         *
         * @param bitmap
         * @param pixels
         * @return
         */
        public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            final float roundPx = pixels;
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        }

    }

    /**
     * 通用字符串(String)相关类,为null时返回""
     *
     * @author Lemon
     * @use StringUtil.
     */
    public static class StringUtil {
        private static final String TAG = "StringUtil";

        public StringUtil() {
        }

        public static final String EMPTY = "无";
        public static final String UNKNOWN = "未知";
        public static final String UNLIMITED = "不限";

        public static final String I = "我";
        public static final String YOU = "你";
        public static final String HE = "他";
        public static final String SHE = "她";
        public static final String IT = "它";

        public static final String MALE = "男";
        public static final String FEMALE = "女";

        public static final String TODO = "未完成";
        public static final String DONE = "已完成";

        public static final String FAIL = "失败";
        public static final String SUCCESS = "成功";

        public static final String SUNDAY = "日";
        public static final String MONDAY = "一";
        public static final String TUESDAY = "二";
        public static final String WEDNESDAY = "三";
        public static final String THURSDAY = "四";
        public static final String FRIDAY = "五";
        public static final String SATURDAY = "六";

        public static final String YUAN = "元";

        private static String currentString = "";

        /**
         * 获取刚传入处理后的string
         *
         * @return
         * @must 上个影响currentString的方法 和 这个方法都应该在同一线程中，否则返回值可能不对
         */
        public static String getCurrentString() {
            return currentString == null ? "" : currentString;
        }

        // 获取string,为null时返回"" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 获取string,为null则返回""
         *
         * @param tv
         * @return
         */
        public static String getString(TextView tv) {
            if (tv == null || tv.getText() == null) {
                return "";
            }
            return getString(tv.getText().toString());
        }

        /**
         * 获取string,为null则返回""
         *
         * @param object
         * @return
         */
        public static String getString(Object object) {
            return object == null ? "" : getString(String.valueOf(object));
        }

        /**
         * 获取string,为null则返回""
         *
         * @param cs
         * @return
         */
        public static String getString(CharSequence cs) {
            return cs == null ? "" : getString(cs.toString());
        }

        /**
         * 获取string,为null则返回""
         *
         * @param s
         * @return
         */
        public static String getString(String s) {
            return s == null ? "" : s;
        }

        // 获取string,为null时返回"" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 获取去掉前后空格后的string<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 获取去掉前后空格后的string,为null则返回""
         *
         * @param tv
         * @return
         */
        public static String getTrimedString(TextView tv) {
            return getTrimedString(getString(tv));
        }

        /**
         * 获取去掉前后空格后的string,为null则返回""
         *
         * @param object
         * @return
         */
        public static String getTrimedString(Object object) {
            return getTrimedString(getString(object));
        }

        /**
         * 获取去掉前后空格后的string,为null则返回""
         *
         * @param cs
         * @return
         */
        public static String getTrimedString(CharSequence cs) {
            return getTrimedString(getString(cs));
        }

        /**
         * 获取去掉前后空格后的string,为null则返回""
         *
         * @param s
         * @return
         */
        public static String getTrimedString(String s) {
            return getString(s).trim();
        }

        // 获取去掉前后空格后的string>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 获取去掉所有空格后的string <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 获取去掉所有空格后的string,为null则返回""
         *
         * @param tv
         * @return
         */
        public static String getNoBlankString(TextView tv) {
            return getNoBlankString(getString(tv));
        }

        /**
         * 获取去掉所有空格后的string,为null则返回""
         *
         * @param object
         * @return
         */
        public static String getNoBlankString(Object object) {
            return getNoBlankString(getString(object));
        }

        /**
         * 获取去掉所有空格后的string,为null则返回""
         *
         * @param cs
         * @return
         */
        public static String getNoBlankString(CharSequence cs) {
            return getNoBlankString(getString(cs));
        }

        /**
         * 获取去掉所有空格后的string,为null则返回""
         *
         * @param s
         * @return
         */
        public static String getNoBlankString(String s) {
            return getString(s).replaceAll(" ", "");
        }

        // 获取去掉所有空格后的string >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 获取string的长度<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 获取string的长度,为null则返回0
         *
         * @param tv
         * @param trim
         * @return
         */
        public static int getLength(TextView tv, boolean trim) {
            return getLength(getString(tv), trim);
        }

        /**
         * 获取string的长度,为null则返回0
         *
         * @param object
         * @param trim
         * @return
         */
        public static int getLength(Object object, boolean trim) {
            return getLength(getString(object), trim);
        }

        /**
         * 获取string的长度,为null则返回0
         *
         * @param cs
         * @param trim
         * @return
         */
        public static int getLength(CharSequence cs, boolean trim) {
            return getLength(getString(cs), trim);
        }

        /**
         * 获取string的长度,为null则返回0
         *
         * @param s
         * @param trim
         * @return
         */
        public static int getLength(String s, boolean trim) {
            s = trim ? getTrimedString(s) : s;
            return getString(s).length();
        }

        // 获取string的长度>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 判断字符是否非空 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 判断字符是否非空
         *
         * @param tv
         * @param trim
         * @return
         */
        public static boolean isNotEmpty(TextView tv, boolean trim) {
            return isNotEmpty(getString(tv), trim);
        }

        /**
         * 判断字符是否非空
         *
         * @param object
         * @param trim
         * @return
         */
        public static boolean isNotEmpty(Object object, boolean trim) {
            return isNotEmpty(getString(object), trim);
        }

        /**
         * 判断字符是否非空
         *
         * @param cs
         * @param trim
         * @return
         */
        public static boolean isNotEmpty(CharSequence cs, boolean trim) {
            return isNotEmpty(getString(cs), trim);
        }

        /**
         * 判断字符是否非空
         *
         * @param s
         * @param trim
         * @return
         */
        public static boolean isNotEmpty(String s, boolean trim) {
            // Log.i(TAG, "getTrimedString s = " + s);
            if (s == null) {
                return false;
            }
            if (trim) {
                s = s.trim();
            }
            if (s.length() <= 0) {
                return false;
            }

            currentString = s;

            return true;
        }

        // 判断字符是否非空 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 判断字符类型 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        // 判断手机格式是否正确
        public static boolean isPhone(String phone) {
            if (isNotEmpty(phone, true) == false) {
                return false;
            }

            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-2,5-9])|(17[0-9]))\\d{8}$");

            currentString = phone;

            return p.matcher(phone).matches();
        }

        // 判断email格式是否正确
        public static boolean isEmail(String email) {
            if (isNotEmpty(email, true) == false) {
                return false;
            }

            String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern p = Pattern.compile(str);

            currentString = email;

            return p.matcher(email).matches();
        }

        // 判断是否全是数字
        public static boolean isNumer(String number) {
            if (isNotEmpty(number, true) == false) {
                return false;
            }

            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(number);
            if (!isNum.matches()) {
                return false;
            }

            currentString = number;

            return true;
        }

        /**
         * 判断字符类型是否是号码或字母
         *
         * @param inputed
         * @return
         */
        public static boolean isNumberOrAlpha(String inputed) {
            if (inputed == null) {
                Log.e(TAG, "isNumberOrAlpha  inputed == null >> return false;");
                return false;
            }
            Pattern pNumber = Pattern.compile("[0-9]*");
            Matcher mNumber;
            Pattern pAlpha = Pattern.compile("[a-zA-Z]");
            Matcher mAlpha;
            for (int i = 0; i < inputed.length(); i++) {
                mNumber = pNumber.matcher(inputed.substring(i, i + 1));
                mAlpha = pAlpha.matcher(inputed.substring(i, i + 1));
                if (!mNumber.matches() && !mAlpha.matches()) {
                    return false;
                }
            }

            currentString = inputed;
            return true;
        }

        /**
         * 判断字符类型是否是身份证号
         *
         * @param idCard
         * @return
         */
        public static boolean isIDCard(String idCard) {
            if (isNumberOrAlpha(idCard) == false) {
                return false;
            }
            idCard = getString(idCard);
            if (idCard.length() == 15) {
                Log.w(TAG, "isIDCard idCard.length() == 15 old IDCard");
                currentString = idCard;
                return true;
            }
            if (idCard.length() == 18) {
                currentString = idCard;
                return true;
            }

            return false;
        }

        public static final String HTTP = "http";
        public static final String URL_PREFIX = "http://";
        public static final String URL_PREFIXs = "https://";
        public static final String URL_STAFFIX = URL_PREFIX;
        public static final String URL_STAFFIXs = URL_PREFIXs;

        /**
         * 判断字符类型是否是网址
         *
         * @param url
         * @return
         */
        public static boolean isUrl(String url) {
            if (isNotEmpty(url, true) == false) {
                return false;
            } else if (!url.startsWith(URL_PREFIX) && !url.startsWith(URL_PREFIXs)) {
                return false;
            }

            currentString = url;
            return true;
        }

        public static final String FILE_PATH_PREFIX = "file://";

        /**
         * 判断文件路径是否存在
         *
         * @param path
         * @return
         */
        public static boolean isFilePathExist(String path) {
            return StringUtil.isFilePath(path) && new File(path).exists();
        }

        /**
         * 判断字符类型是否是路径
         *
         * @param path
         * @return
         */
        public static boolean isFilePath(String path) {
            if (isNotEmpty(path, true) == false) {
                return false;
            }

            if (!path.contains(".") || path.endsWith(".")) {
                return false;
            }

            currentString = path;

            return true;
        }

        // 判断字符类型
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 提取特殊字符<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 去掉string内所有非数字类型字符
         *
         * @param tv
         * @return
         */
        public static String getNumber(TextView tv) {
            return getNumber(getString(tv));
        }

        /**
         * 去掉string内所有非数字类型字符
         *
         * @param object
         * @return
         */
        public static String getNumber(Object object) {
            return getNumber(getString(object));
        }

        /**
         * 去掉string内所有非数字类型字符
         *
         * @param cs
         * @return
         */
        public static String getNumber(CharSequence cs) {
            return getNumber(getString(cs));
        }

        /**
         * 去掉string内所有非数字类型字符
         *
         * @param s
         * @return
         */
        public static String getNumber(String s) {
            if (isNotEmpty(s, true) == false) {
                return "";
            }

            String numberString = "";
            String single;
            for (int i = 0; i < s.length(); i++) {
                single = s.substring(i, i + 1);
                if (isNumer(single)) {
                    numberString += single;
                }
            }

            return numberString;
        }

        // 提取特殊字符>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // 校正（自动补全等）字符串<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        /**
         * 获取网址，自动补全
         *
         * @param tv
         * @return
         */
        public static String getCorrectUrl(TextView tv) {
            return getCorrectUrl(getString(tv));
        }

        /**
         * 获取网址，自动补全
         *
         * @param url
         * @return
         */
        public static String getCorrectUrl(String url) {
            Log.i(TAG, "getCorrectUrl : \n" + url);
            if (isNotEmpty(url, true) == false) {
                return "";
            }

            if (!url.endsWith("/") && !url.endsWith(".html")) {
                url = url + "/";
            }

            if (isUrl(url) == false) {
                return URL_PREFIX + url;
            }
            return url;
        }

        /**
         * 获取去掉所有 空格 、"-" 、"+86" 后的phone
         *
         * @param tv
         * @return
         */
        public static String getCorrectPhone(TextView tv) {
            return getCorrectPhone(getString(tv));
        }

        /**
         * 获取去掉所有 空格 、"-" 、"+86" 后的phone
         *
         * @param phone
         * @return
         */
        public static String getCorrectPhone(String phone) {
            if (isNotEmpty(phone, true) == false) {
                return "";
            }

            phone = getNoBlankString(phone);
            phone = phone.replaceAll("-", "");
            if (phone.startsWith("+86")) {
                phone = phone.substring(3);
            }
            return phone;
        }

        /**
         * 获取邮箱，自动补全
         *
         * @param tv
         * @return
         */
        public static String getCorrectEmail(TextView tv) {
            return getCorrectEmail(getString(tv));
        }

        /**
         * 获取邮箱，自动补全
         *
         * @param email
         * @return
         */
        public static String getCorrectEmail(String email) {
            if (isNotEmpty(email, true) == false) {
                return "";
            }

            email = getNoBlankString(email);
            if (isEmail(email) == false && !email.endsWith(".com")) {
                email += ".com";
            }

            return email;
        }

        public static final int PRICE_FORMAT_DEFAULT = 0;
        public static final int PRICE_FORMAT_PREFIX = 1;
        public static final int PRICE_FORMAT_SUFFIX = 2;
        public static final int PRICE_FORMAT_PREFIX_WITH_BLANK = 3;
        public static final int PRICE_FORMAT_SUFFIX_WITH_BLANK = 4;
        public static final String[] PRICE_FORMATS = {"", "￥", "元", "￥ ", " 元"};

        /**
         * 获取价格，保留两位小数
         *
         * @param price
         * @return
         */
        public static String getPrice(String price) {
            return getPrice(price, PRICE_FORMAT_DEFAULT);
        }

        /**
         * 获取价格，保留两位小数
         *
         * @param price
         * @param formatType 添加单位（元）
         * @return
         */
        public static String getPrice(String price, int formatType) {
            if (isNotEmpty(price, true) == false) {
                return getPrice(0, formatType);
            }

            // 单独写到getCorrectPrice? <<<<<<<<<<<<<<<<<<<<<<
            String correctPrice = "";
            String s;
            for (int i = 0; i < price.length(); i++) {
                s = price.substring(i, i + 1);
                if (".".equals(s) || isNumer(s)) {
                    correctPrice += s;
                }
            }
            // 单独写到getCorrectPrice? >>>>>>>>>>>>>>>>>>>>>>

            Log.i(TAG, "getPrice  <<<<<<<<<<<<<<<<<< correctPrice =  " + correctPrice);
            if (correctPrice.contains(".")) {
                // if (correctPrice.startsWith(".")) {
                // correctPrice = 0 + correctPrice;
                // }
                if (correctPrice.endsWith(".")) {
                    correctPrice = correctPrice.replaceAll(".", "");
                }
            }

            Log.i(TAG, "getPrice correctPrice =  " + correctPrice + " >>>>>>>>>>>>>>>>");
            return isNotEmpty(correctPrice, true) ? getPrice(new BigDecimal(0 + correctPrice), formatType)
                    : getPrice(0, formatType);
        }

        /**
         * 获取价格，保留两位小数
         *
         * @param price
         * @return
         */
        public static String getPrice(BigDecimal price) {
            return getPrice(price, PRICE_FORMAT_DEFAULT);
        }

        /**
         * 获取价格，保留两位小数
         *
         * @param price
         * @return
         */
        public static String getPrice(double price) {
            return getPrice(price, PRICE_FORMAT_DEFAULT);
        }

        /**
         * 获取价格，保留两位小数
         *
         * @param price
         * @param formatType 添加单位（元）
         * @return
         */
        public static String getPrice(BigDecimal price, int formatType) {
            return getPrice(price == null ? 0 : price.doubleValue(), formatType);
        }

        /**
         * 获取价格，保留两位小数
         *
         * @param price
         * @param formatType 添加单位（元）
         * @return
         */
        public static String getPrice(double price, int formatType) {
            String s = new DecimalFormat("#########0.00").format(price);
            switch (formatType) {
                case PRICE_FORMAT_PREFIX:
                    return PRICE_FORMATS[PRICE_FORMAT_PREFIX] + s;
                case PRICE_FORMAT_SUFFIX:
                    return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX];
                case PRICE_FORMAT_PREFIX_WITH_BLANK:
                    return PRICE_FORMATS[PRICE_FORMAT_PREFIX_WITH_BLANK] + s;
                case PRICE_FORMAT_SUFFIX_WITH_BLANK:
                    return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX_WITH_BLANK];
                default:
                    return s;
            }
        }

        // 校正（自动补全等）字符串>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    }

    /**
     * 屏幕相关类
     *
     * @author Lemon
     * @use ScreenUtil.
     */
    public static class ScreenUtil {
        // private static final String TAG = "SceenUtil";

        private ScreenUtil() {
            /* 不能实例化 **/
        }

        public static int[] screenSize;

        public static int[] getScreenSize(Context context) {
            if (screenSize == null || screenSize[0] <= 480 || screenSize[1] <= 800) {// 小于该分辨率会显示不全
                screenSize = new int[2];

                DisplayMetrics dm = new DisplayMetrics();
                dm = context.getResources().getDisplayMetrics();
                // float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
                // int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
                // float xdpi = dm.xdpi;
                // float ydpi = dm.ydpi;
                // Log.e(TAG + " DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" +
                // ydpi);
                // Log.e(TAG + " DisplayMetrics", "density=" + density + ";
                // densityDPI="
                // + densityDPI);

                screenSize[0] = dm.widthPixels;// 屏幕宽（像素，如：480px）
                screenSize[1] = dm.heightPixels;// 屏幕高（像素，如：800px）
            }

            return screenSize;
        }

        public static int getScreenWidth(Context context) {
            return getScreenSize(context)[0];
        }

        public static int getScreenHeight(Context context) {
            return getScreenSize(context)[1];
        }

    }

}
