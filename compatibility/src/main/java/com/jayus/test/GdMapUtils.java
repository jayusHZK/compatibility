package com.jayus.test;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * @author : h zk
 * @date : 2022/11/3 16:54
 * @description :
 **/
public class GdMapUtils {
    public static final String GD_URL = "https://restapi.amap.com/v3/geocode/geo?key=e0b13fb19d267cc13f76e91d544fa18b&address=";

    /**
     * 根据地址获取经纬度信息
     */
    public static Object getGeographyInfoByAddress(String address) {
        String result = "";
        try {
            // 把字符串转换为URL请求地址
            URL url = new URL(GD_URL + address);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 连接会话
            connection.connect();
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            // 循环读取流
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            HashMap map = JSON.parseObject(sb.toString(), HashMap.class);
            Object geocodes = map.get("geocodes");
            System.out.println(geocodes);
            return JSON.parseArray(JSON.toJSONString(geocodes)).getJSONObject(0).getString("location");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(getGeographyInfoByAddress("澳大利亚堪培拉"));
    }

}
