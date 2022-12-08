package com.jayus.test;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author : h zk
 * @date : 2022/11/4 15:36
 * @description :
 **/
public class DataTest {

    public static void main(String[] args) {
        long a = 1;
        Long b = new Long(1);
        System.out.println(a == b);

        JSONObject param = new JSONObject();
        param.put("styleId", 1);
        HttpResponse execute = HttpUtil.createPost("http://47.107.48.191:14008////v1.1.0/theme/list/").body(String.valueOf(param))
                .execute();
        System.out.println(execute.body());

        System.out.println(DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 2));
        System.out.println(DateUtil.offset(DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, -3), DateField.DAY_OF_MONTH, 6));

        System.out.println(Date.from(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        System.out.println(Date.from(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
}
