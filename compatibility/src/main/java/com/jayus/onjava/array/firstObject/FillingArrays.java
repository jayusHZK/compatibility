package com.jayus.onjava.array.firstObject;

import java.util.Arrays;

/**
 * @author : h zk
 * @date : 2022/8/11 15:04
 * @description :
 **/
public class FillingArrays {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = new boolean[size];
        byte[] a2 = new byte[size];
        char[] a3 = new char[size];
        short[] a4 = new short[size];
        int[] a5 = new int[size];
        long[] a6 = new long[size];
        float[] a7 = new float[size];
        double[] a8 = new double[size];
        String[] a9 = new String[size];
        Arrays.fill(a1,true);
        System.out.println(Arrays.toString(a1));
        Arrays.fill(a2, (byte)11);
        System.out.println(Arrays.toString(a2));
        Arrays.fill(a3, 'x');
        System.out.println(Arrays.toString(a3));

    }
}
