package com.jayus.onjava.stream.collectors;

import static com.jayus.onjava.stream.optional.RandInts.*;

/**
 * @author : h zk
 * @date : 2022/7/28 17:46
 * @description :
 **/
public class NumericStreamInfo {
    public static void main(String[] args) {
        // 生成平均数
        System.out.println(rands().average().getAsDouble());
        // 最大值
        System.out.println(rands().max().getAsInt());
        //最小值
        System.out.println(rands().min().getAsInt());
        // 总和
        System.out.println(rands().sum());
        System.out.println(rands().count());
        // 获取以上所有
        System.out.println(rands().summaryStatistics());
    }
}
