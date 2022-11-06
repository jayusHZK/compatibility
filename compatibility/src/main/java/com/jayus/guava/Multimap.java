package com.jayus.guava;

import com.google.common.collect.ArrayListMultimap;

import java.util.List;

public class Multimap {
    // 多值map
    public static void main(String[] args) {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("a","1");
        multimap.put("a","2");
        multimap.put("a","3");
        multimap.put("a","4");
        System.out.println(multimap);
        // get 操作将返回 集合对象
        List<String> a = multimap.get("a");


    }
}
