package com.jayus.onjava.stream.optional;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 10:01
 * @description :
 **/
public class OptionalMap {
    static String[] elements = {"1", "", "3", "4"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, String> func) {
        System.out.println("===" + descr + "===");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(testStream()
                    .skip(i)
                    .findFirst()
                    .map(func));
        }
    }

    public static void main(String[] args) {
        test("Add brackets", s -> "[" + s + "]");
        test("Increment", s -> {
            try {
                return Integer.valueOf(s) + "";
            } catch (Exception e) {
                return s;
            }
        });
        test("Replace", s -> s.replace("3","5"));
        test("Take last digit",s -> s.length() > 0? s.charAt(s.length() -1) + "" : s);
        System.out.println("a".charAt("a".length() - 1));
    }

}
