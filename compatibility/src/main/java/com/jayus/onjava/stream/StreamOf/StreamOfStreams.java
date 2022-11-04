package com.jayus.onjava.stream.StreamOf;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 17:04
 * @description :
 **/
/*
flatMap() 做了两件事：将产生流的函数应用在每个元素上（与 map() 所做的相同），然后将每个流都扁平化为元素，因而最终产生的仅仅是元素。
 */
public class StreamOfStreams {
    public static void main(String[] args) {
        Stream.of(1,2,3)
                .map(i -> Stream.of("a","b","c"))
                .map(e -> e.getClass().getName())
                .forEach(System.out::println);
    }
}
