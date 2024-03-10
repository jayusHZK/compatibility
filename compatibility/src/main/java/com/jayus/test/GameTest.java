package com.jayus.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @author : h zk
 * @date : 2022/11/23 11:09
 * @description :
 **/
public class GameTest {

    private static Integer[] bossArr = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) throws InterruptedException {
        String s = "a";
        s.length();

        DateTime date = DateUtil.parse("2022/11/28");
        //int i = DateUtil.w(date);
        //System.out.println(i);
        //bossArr[DateUtil.dayOfWeek(date) % bossArr.length];
    }
}
