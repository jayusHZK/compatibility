package com.jayus.guava;

import com.google.common.collect.HashBiMap;

public class BiMap {
    // key和value双向关联的数据结构
    public static void main(String[] args) {
        HashBiMap<String,String> biMap = HashBiMap.create();
        biMap.put("qiu","min");
        biMap.put("qi","lin");
        System.out.println(biMap.get("qiu"));
        // 反转
        com.google.common.collect.BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse.get("lin"));
    }
}
