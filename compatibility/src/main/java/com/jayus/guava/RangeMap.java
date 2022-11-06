package com.jayus.guava;

import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;

public class RangeMap {
    // 描述区间到特定值的映射关系
    public static void main(String[] args) {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0,60),"fail");
        rangeMap.put(Range.closedOpen(60,90),"satisfactory");
        rangeMap.put(Range.closedOpen(90,100),"excellent");
        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));
    }
}
