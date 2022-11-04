package com.jayus.onjava.stream.collectors.match;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 16:06
 * @description :
 **/
/*
IntStream 类提供了 range() 方法用于生成整型序列的流
 */
public class Matching {
    static void show(Matcher match,int val) {
        System.out.println(match.test(IntStream.rangeClosed(1,9)
        .boxed().peek(n -> System.out.format("%d ",n)),n -> n < val));
    }

    public static void main(String[] args) {
        // allMatch 如果流的每个元素提供给Predicate都返回true，结果返回true。再第一个false时，则停止执行计算
        show(Stream::allMatch,10);
        show(Stream::allMatch,4);
        // anyMatch(Predicate)：如果流的任意一个元素提供给 Predicate 返回 true ，结果返回为 true。在第一个 true 是停止执行计算
        show(Stream::anyMatch,2);
        show(Stream::anyMatch,0);
        // noneMatch(Predicate)：如果流的每个元素提供给 Predicate 都返回 false 时，结果返回为 true。在第一个 true 时停止执行计算
        show(Stream::noneMatch,5);
        show(Stream::noneMatch,0);
    }
}
