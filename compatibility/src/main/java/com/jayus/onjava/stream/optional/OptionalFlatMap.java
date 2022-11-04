package com.jayus.onjava.stream.optional;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 10:22
 * @description :
 **/
public class OptionalFlatMap {
    static String[] elements = {"1", "2", "", "4"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, Optional<String>> func) {
        System.out.println("===" + descr + "===");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(testStream()
                    .skip(i)
                    .findFirst()
                    .flatMap(func));
        }
    }

    public static void main(String[] args) {
        test("Add brackets", s -> Optional.of("[" + s + "]"));
        test("Increment", s -> {
            try {
                return Optional.of(Integer.valueOf(s) + "");
            } catch (Exception e) {
                return Optional.of(s);
            }
        });
        test("Replace", s -> Optional.of(s.replace("2", "3")));
        test("Take last digit", s ->
                Optional.of(s.length() > 0 ? s.charAt(s.length() - 1) + "" : s));
    }


}
