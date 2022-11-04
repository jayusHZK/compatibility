package com.jayus.onjava.stream.collectors;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/7/28 17:38
 * @description :
 **/
public class LastElement {
    public static void main(String[] args) {
        OptionalInt last = IntStream.range(10, 20)
                .reduce((n1, n2) -> {
                    System.out.println(n1+" "+n2);
                    return n2;
                });
        System.out.println(last.orElse(-1));



    }
}
