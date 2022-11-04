package com.jayus.onjava.stream.StreamOf;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 11:37
 * @description :
 **/
/*
Stream.iterate() 产生的流的第一个元素是种子（iterate方法的第一个参数），然后将种子传递给方法（iterate方法的第二个参数）
 */
public class Fibonacci {
    int x = 1;

    Stream<Integer> numbers() {
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }

    public static void main(String[] args) {
        new Fibonacci().numbers()
                .skip(20)
                .limit(10)
                .forEach(System.out::println);
    }
}
