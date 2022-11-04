package com.jayus.onjava.stream.StreamOf;

import java.util.*;

/**
 * @author : h zk
 * @date : 2022/7/26 17:16
 * @description :
 **/
public class CollectionToStream {
    public static void main(String[] args) {
        List<Bubble> bubbles = Arrays.asList(new Bubble(1), new Bubble(2), new Bubble(3));
        System.out.println(bubbles.stream().mapToInt(b -> b.age).sum());

        Set<String> set = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        set.stream().map(x -> x + " ").forEach(System.out::println);

        Map<String,Double> map = new HashMap<>();
        map.put("a",1D);
        map.put("b",2D);
        map.put("c",3D);
        map.entrySet().stream()
                .map(e -> e.getKey() + e.getValue())
                .forEach(System.out::println);
    }
}
