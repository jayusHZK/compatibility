package com.jayus.onjava.array.firstObject;

import java.util.Arrays;
import java.util.SplittableRandom;

/**
 * @author : h zk
 * @date : 2022/8/11 14:45
 * @description :
 **/
public class RaggedArray {
    static int val = 1;

    public static void main(String[] args) {
        SplittableRandom rand = new SplittableRandom(47);
        int[][][] a = new int[rand.nextInt(7)][][];
        for (int i = 0; i < a.length; i++) {
            System.out.println(i+" ");
            a[i] = new int[rand.nextInt(5)][];
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(j+" ");
                a[i][j] = new int[rand.nextInt(5)];
                Arrays.setAll(a[i][j],n -> val++);
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
}
