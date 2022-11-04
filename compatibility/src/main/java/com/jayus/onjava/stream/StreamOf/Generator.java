package com.jayus.onjava.stream.StreamOf;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 10:22
 * @description :
 **/
public class Generator implements Supplier<String> {

    Random rand = new Random(47);

    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public String get() {
        return " " + letters[rand.nextInt(letters.length)];
    }

    public static void main(String[] args) {
        System.out.println(Stream.generate(new Generator())
                .limit(30)
                .collect(Collectors.joining()));
    }
}
