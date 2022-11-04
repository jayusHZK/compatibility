package com.jayus.onjava.stream.optional;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/22 16:15
 * @description :
 **/
/*
filter(Predicate)：对 Optional 中的内容应用Predicate 并将结果返回。如果 Optional 不满足 Predicate ，将 Optional 转化为空 Optional 。如果 Optional 已经为空，则直接返回空Optional 。

map(Function)：如果 Optional 不为空，应用 Function 于 Optional 中的内容，并返回结果。否则直接返回 Optional.empty。

flatMap(Function)：同 map()，但是提供的映射函数将结果包装在 Optional 对象中，因此 flatMap() 不会在最后进行任何包装。
 */
public class OptionalFilter {
    static String[] elements = {"Foo", "", "Bar", "Baz", "Bingo"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Predicate<String> pred){
        System.out.println("===" + descr + "===");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(testStream().skip(i).findFirst().filter(pred));
        }
    }

    public static void main(String[] args) {
        test("true",str -> true);
        test("false",str -> false);
        test("str != \"\"",str -> str != "");
        test("str.length() == 3",str -> str.length() == 3);
        test("startWith(\"B\")",str -> str.startsWith("B"));
    }
}
