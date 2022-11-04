package com.jayus.onjava.stream.terminal;

/**
 * @author : h zk
 * @date : 2022/8/22 17:08
 * @description :
 **/
public class Pair {
    public final Character c;

    public final Integer i;

    public Pair(Character c, Integer i) {
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

