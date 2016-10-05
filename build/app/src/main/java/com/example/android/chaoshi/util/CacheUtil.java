package com.example.android.chaoshi.util;

import android.support.v4.util.LruCache;

import com.example.android.chaoshi.base.ProjectUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/14/014.
 */
public class CacheUtil {
    private static LruCache<String, String> mMap = new LruCache<String, String>(4000);



    // 写缓存
    // 以url为key, 以json为value
    public void setCache(String url, String value) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = ProjectUtil.getContext().getCacheDir();// 本应用的缓存文件夹
        // 生成缓存文件
        File cacheFile = new File(cacheDir, ProjectUtil.MD5.encrypt16byte(url));

        FileWriter writer = null;
        try {
            writer = new FileWriter(cacheFile);
            // 缓存失效的截止时间
            long deadline = System.currentTimeMillis() + 30 * 60 * 1000;// 半个小时有效期
            writer.write(deadline + "\n");// 在第一行写入缓存时间, 换行
            writer.write(value);// 写入json
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ProjectUtil.close(writer);
        }
    }




    // 读缓存
    public String getCache(String url) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = ProjectUtil.getContext().getCacheDir();// 本应用的缓存文件夹
        // 生成缓存文件
        File cacheFile = new File(cacheDir, ProjectUtil.MD5.encrypt16byte(url));
        // 判断缓存是否存在
        if (cacheFile.exists()) {
            // 判断缓存是否有效
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                String deadline = reader.readLine();// 读取第一行的有效期
                long deadtime = Long.parseLong(deadline);
                if (System.currentTimeMillis() < deadtime) {// 当前时间小于截止时间,
                    // 说明缓存有效
                    // 缓存有效
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ProjectUtil.close(reader);
            }
        }
        return "";
    }

    public interface HttpCacheCallBack {
        void onSuccessHttpCacheCallBack(String result);
    }
}
