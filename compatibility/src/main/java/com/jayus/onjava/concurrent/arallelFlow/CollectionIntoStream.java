package com.jayus.onjava.concurrent.arallelFlow;

import com.jayus.onjava.stream.StreamOf.Generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/15 9:34
 * @description :
 **/
public class CollectionIntoStream {
    public static void main(String[] args) {
        List<String> strings = Stream.generate(new Generator())
                .limit(10)
                .collect(Collectors.toList());
        strings.forEach(System.out::println);

        String result = strings.stream()
                .map(String::toUpperCase)
                .map(s -> s.substring(2))
                .reduce(":", (s1, s2) -> s1 + s2);
        System.out.println(result);
    }
}
