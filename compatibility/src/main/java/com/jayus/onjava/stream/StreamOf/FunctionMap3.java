package com.jayus.onjava.stream.StreamOf;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 16:22
 * @description :
 **/
public class FunctionMap3 {
    public static void main(String[] args) {
        Stream.of("5","7","9")
                .mapToInt(Integer::parseInt)
                .forEach(System.out::println);
        System.out.println();

    }
}
