package com.jayus.onjava.array.firstObject;

import java.util.Arrays;

/**
 * @author : h zk
 * @date : 2022/8/11 15:55
 * @description :
 **/
public class SimpleSetAll {
    public static final int SZ = 8;
    static int val = 1;
    static char[] chars = "abcdefghijklmnopqrstuvwxyz"
            .toCharArray();
    static char getChar(int n){
        return chars[n];
    }
    public static void main(String[] args) {
        int[] ia = new int[SZ];
        long[] la = new long[SZ];
        double[] da = new double[SZ];
        Arrays.setAll(ia, n -> n); // [1]
        Arrays.setAll(la, n -> n);
        Arrays.setAll(da, n -> n);
        System.out.println(Arrays.toString(ia));

    }
}
