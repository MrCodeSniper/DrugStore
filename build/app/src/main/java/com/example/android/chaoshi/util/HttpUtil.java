/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.example.android.chaoshi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    private static final int READ_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;

    public static String executeGet(String urlString) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = executeGetInputStream(urlString);
            outputStream = new ByteArrayOutputStream(1024);
            int readLength = 0;
            byte[] buffer = new byte[4 * 1024];
            while ((readLength = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, readLength);
            }
            String json = outputStream.toString("gbk");
            return json;
        } catch (Exception e) {

        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static InputStream executeGetInputStream(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 指的是与请求网址的服务器建立连接的超时时间。
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        // 指的是建立连接后如果指定时间内服务器没有返回数据的后超时。
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setRequestMethod("GET");
        return connection.getInputStream();
    }
}
