package com.jayus.onjava.array.firstObject;

import java.util.Arrays;

/**
 * @author : h zk
 * @date : 2022/8/11 14:41
 * @description :
 **/
public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a= {{1,2,3},{4,5,6}};
        System.out.println(Arrays.deepToString(a));
        int[][][] b = new int[2][3][4];
    }
}
