package com.jayus.onjava.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiFunction;

/**
 * @author : h zk
 * @date : 2022/8/11 16:33
 * @description :
 **/
public class arrayParallel {
    public static void main(String[] args) {
        Collections.reverseOrder();
        //Arrays.parallelSort(); //并行排序
        //Arrays.binarySearch() // 二分查找
        //Arrays.setAll();
        biFunctiontest("1", (s, i) -> Integer.valueOf(s)+i, 3);
    }

    public static void biFunctiontest(String b,BiFunction<String,Integer,Integer> function,Integer i){
        Integer apply = function.apply(b, i);
        System.out.println(apply);
    }
}
