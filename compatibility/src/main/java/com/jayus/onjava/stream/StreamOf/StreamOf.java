package com.jayus.onjava.stream.StreamOf;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/26 17:12
 * @description :
 **/
public class StreamOf {
    public static void main(String[] args) {
        // 通过 Stream.of() 很容易地将一组元素转化成为流
        Stream.of(new Bubble(1),new Bubble(2),new Bubble(3))
                .forEach(System.out::println);
        Stream.of("a","b","c")
                .forEach(System.out::println);

    }
}
