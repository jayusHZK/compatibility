package com.jayus.onjava.stream.collectors.custom;

/**
 * @author : h zk
 * @date : 2022/7/28 14:21
 * @description :
 **/
public class Pair {
    public final Character c;

    public final Integer i;

    Pair(Character c, Integer i) {
        this.c = c;
        this.i = i;
    }

    public Character getC() {
        return c;
    }

    public Integer getI() {
        return i;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "c=" + c +
                ", i=" + i +
                '}';
    }
}
