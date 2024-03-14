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
            o = (s) -> {
        System.out.println(s);
        return !s.contains("a");
    },
    p1 = (s) -> {
        System.out.println("p1");
        return !s.contains("bar");
    },
    p2 = (s) -> {
        System.out.println("p2");
        return s.length() < 5;},
    p3 = (s) -> {
        System.out.println("p3");
        return s.contains("foo");
        },
    p4 = p1.negate().and(p2).or(p3);

    public static void main(String[] args) {
        Predicate p = (str) ->{
            System.out.println(str);
            return true;
        };
        System.out.println(o.negate().and(p).test("abc"));

        Stream.of("bar","foobar","foobaz","fongopuckey")
                .filter(p4)
                .forEach(System.out::println);
    }
}
