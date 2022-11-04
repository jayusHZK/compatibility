package com.jayus.onjava.stream.collectors.reduce;

import java.util.Random;

/**
 * @author : h zk
 * @date : 2022/7/28 15:52
 * @description :
 **/
public class Frobnitz {
    int size;

    Frobnitz(int sz) {
        this.size = sz;
    }

    @Override
    public String toString() {
        return "Frobnitz{" +
                "size=" + size +
                '}';
    }

    static Random rand = new Random(47);

    static final int BOUND = 100;

    static Frobnitz supply(){
        return new Frobnitz(rand.nextInt(BOUND));
    }

}
