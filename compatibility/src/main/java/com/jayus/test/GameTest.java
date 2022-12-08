package com.jayus.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.net.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author : h zk
 * @date : 2022/11/23 11:09
 * @description :
 **/
public class GameTest {

    private static Integer[] bossArr = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) throws InterruptedException {
        DateTime date = DateUtil.parse("2022/11/28");
        //int i = DateUtil.w(date);
        //System.out.println(i);
        //bossArr[DateUtil.dayOfWeek(date) % bossArr.length];
    }
}
