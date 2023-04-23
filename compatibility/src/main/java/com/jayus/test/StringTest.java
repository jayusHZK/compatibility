package com.jayus.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : h zk
 * @date : 2023/3/20 11:20
 * @description :
 **/
public class StringTest {

    public static void main(String[] args) {
        Integer d = 123;
        String format = String.format("%06d", d);
        System.out.println(format);

        String c = "1633802073768198144";
        System.out.println(Long.valueOf(c));

        String a = "";
        String b = "1";
        System.out.println(StringUtils.isAnyEmpty(a,b));
    }

}
