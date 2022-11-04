package com.jayus.onjava.stream.terminal;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/22 17:25
 * @description :
 **/
public class Frobnitz {
    int size;

    public Frobnitz(int size) {
        this.size = size;
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

    public static void main(String[] args) {
        Stream.generate(Frobnitz::supply)
                .limit(10)
                .peek(System.out::println)
                .reduce((fr0,fr1) -> fr0.size < 50? fr0:fr1)
                .ifPresent(System.out::println);

    }
}
