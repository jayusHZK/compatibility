package com.jayus.test;

import cn.hutool.core.util.HashUtil;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/22 23:08
 * @Version: 1.0
 */
public class TongyiTest {

    public static void main(String[] args) {
        System.out.println(HashUtil.fnvHash("1"));
        System.out.println(getVal('c'));
    }

    private static int getVal(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 97;
        } else {
            return c >= 'A' && c <= 'Z' ? c - 65 + 26 : c - 48 + 52;
        }
    }

}