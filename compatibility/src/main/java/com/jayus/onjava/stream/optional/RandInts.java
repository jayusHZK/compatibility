package com.jayus.onjava.stream.optional;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 13:46
 * @description :
 **/
public class RandInts {
    private static int[] rints = new Random(47).ints(0,1000).limit(100).toArray();
    public static IntStream rands () {
        return Arrays.stream(rints);
    }

    public static void main(String[] args) {
        rands().parallel().forEach(System.out::print);
        System.out.println("---");
        // forEachOrdered 保证forEach按照原始流顺序操作
        rands().parallel().forEachOrdered(System.out::print);
    }
}
