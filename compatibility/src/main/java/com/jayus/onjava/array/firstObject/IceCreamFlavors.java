package com.jayus.onjava.array.firstObject;

import java.util.Arrays;
import java.util.SplittableRandom;

/**
 * @author : h zk
 * @date : 2022/8/11 14:32
 * @description :
 **/
public class IceCreamFlavors {
    private static SplittableRandom rand = new SplittableRandom(47);
    static final String[] FLAVORS = {"a", "b", "c", "d","e","f","g"};

    public static String[] flavorSet(int n) {
        if (n > FLAVORS.length)
            throw new IllegalArgumentException();
        String[] result = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];
        for (int i = 0; i < n; i++) {
            int t;
            do
                t=rand.nextInt(FLAVORS.length);
            while (picked[t]);
            result[i] = FLAVORS[t];
            picked[t] = true;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(flavorSet(i)));
        }
    }
}
