package com.jayus.test;

import java.util.Date;

/**
 * @author : h zk
 * @date : 2022/11/4 15:36
 * @description :
 **/
public class DataTest {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(new Date(l));
    }
}
