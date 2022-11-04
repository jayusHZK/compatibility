package com.jayus.onjava.stream.StreamOf;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 15:24
 * @description :
 **/
public class FunctionMap {
    static String[] elements = {"12", "", "23", "45"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, String> func) {
        System.out.println(" ---( " + descr + " )--- ");
        testStream().map(func)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        test("add brackets", s -> "[" + s + "]");
        test("Increment",s -> {
            try {
                return Integer.parseInt(s) + "";
            } catch (NumberFormatException e) {
                return s;
            }
        });
        test("Replace", s -> s.replace("2","3"));
        test("Take last digit", s -> s.length() > 0?s.charAt(s.length() - 1)+ "" : s);
    }
}
