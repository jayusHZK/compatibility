package com.jayus.onjava.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/8 18:14
 * @description :
 **/
public class RandomList<T> extends ArrayList<T> {
    private Random random = new Random(47);

    public T select() {
        return get(random.nextInt(size()));
    }

    private static int c = 1;
    private static final int d = c++;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++){
            System.out.println(d);
        }

        RandomList<String> rs = new RandomList<>();
        Arrays.stream("can you speak japanese?".split(" ")).forEach(rs::add);
        IntStream.range(0, 11).forEach(i -> {
            System.out.println(rs.select() + " ");
        });
    }
}
