package com.jayus.onjava.stream.StreamOf;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 11:20
 * @description :
 **/
public class Duplicator {
    public static void main(String[] args) {
        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);

        Stream.generate(String::new)
                .limit(10)
                .forEach(System.out::println);
    }
}
