package com.jayus.onjava.stream.StreamOf;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 17:01
 * @description :
 **/
public class FlatMap {
    public static void main(String[] args) {
        Stream.of(1,2,3)
                .flatMap(i -> Stream.of("Gonzo","fozzie","Beaker"))
                .forEach(System.out::println);
    }
}
