package com.jayus.onjava.stream.collectors.reduce;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 15:54
 * @description :
 **/
public class Reduce {
    public static void main(String[] args) {
        Stream.generate(Frobnitz::supply)
                .limit(10)
                .peek(System.out::println)
                // 使用BinaryOrerator来组合所有流中的元素
                .reduce((fr0,fr1) -> fr0.size < 50? fr0:fr1)
                .ifPresent(System.out::println);
    }
}
