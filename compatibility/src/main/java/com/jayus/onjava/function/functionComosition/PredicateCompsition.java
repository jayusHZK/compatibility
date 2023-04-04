package com.jayus.onjava.function.functionComosition;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/22 17:43
 * @description :
 **/
public class PredicateCompsition {
    static Predicate<String>
    p1 = s -> !s.contains("bar"),
    p2 = s -> s.length() < 5,
    p3 = s -> s.contains("foo"),
    p4 = p1.negate().and(p2).or(p3);

    public static void main(String[] args) {

        System.out.println(p1.negate().test("a"));

        Stream.of("bar","foobar","foobaz","fongopuckey")
                .filter(p4)
                .forEach(System.out::println);
    }
}
