package com.example1.demo1.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 0:05
 */
public class HttpUrlConnectionUtils {

    public static String sendPostRequest(Object param, String hurl) throws Exception {
        URL url = new URL(hurl);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("POST");
        // 设置doOutput属性为true表示将使用此urlConnection写入数据
        urlConnection.setDoOutput(true);
        // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
        urlConnection.setRequestProperty("Content-Type", "application/encrypt-json");
        // 得到请求的输出流对象
        OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());


        String jsonStr = JSON.toJSONString(param);
        System.out.println("请求参数：" + jsonStr);
        out.write(jsonStr);
        out.flush();
        out.close();
        // 从服务器读取响应
        InputStream inputStream = urlConnection.getInputStream();
        String encoding = urlConnection.getContentEncoding();
        String body = IOUtils.toString(inputStream, encoding);
        return body;
    }

    public static String sendGetRequest(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestMethod("GET");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }

}
