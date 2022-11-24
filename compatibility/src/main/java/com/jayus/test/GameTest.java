package com.jayus.test;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.net.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

/**
 * @author : h zk
 * @date : 2022/11/23 11:09
 * @description :
 **/
public class GameTest {

    Class clazz = getClass();

    public static void main(String[] args) throws InterruptedException {
        GameTest gameTest = new GameTest();
        System.out.println(gameTest.clazz.getSimpleName());
        for (int i = 0; i < 1000; i++) {
            System.out.println("当前执行到" + i);
            TimeUnit.SECONDS.sleep(1L);
            JSONObject heroParam = new JSONObject();
            heroParam.put("uid", i);
            JSONArray heroJsonArray = new JSONArray();
            JSONObject hero = new JSONObject();
            hero.put("heroId","1");
            heroJsonArray.add(hero);
            heroParam.put("heroList",heroJsonArray);
            HttpUtil.createPost("http://localhost:11000/hero/report")
                    .body(String.valueOf(heroParam)).execute();

            JSONObject itemParam = new JSONObject();
            itemParam.put("uid", i);
            JSONArray itemJsonArray = new JSONArray();
            JSONObject item = new JSONObject();
            item.put("itemsId","1");
            itemJsonArray.add(item);
            itemParam.put("gameUserItemNewList",itemJsonArray);
            HttpUtil.createPost("http://localhost:11000/item/report")
                    .body(String.valueOf(itemParam)).execute();
        }

    }
}
