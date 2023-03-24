package com.jayus.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : h zk
 * @date : 2023/3/20 11:20
 * @description :
 **/
public class StringTest {

    public static void main(String[] args) {
        String c = "1633802073768198144";
        System.out.println(Long.valueOf(c));

        String a = "";
        String b = "1";
        System.out.println(StringUtils.isAnyEmpty(a,b));
    }

}
