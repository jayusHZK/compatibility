package com.jayus.onjava.enums;

import jdk.nashorn.internal.ir.BreakableNode;

import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/11 19:42
 * @description :
 **/
public enum Signal {
    GREEN, YELLOW, RED;

    public static void main(String[] args) {
        Signal color = Signal.RED;
        for (int i = 0; i < 3; i++) {
            switch (color){
                case GREEN:
                    color = RED;
                    System.out.println(color);
                    break;
                case RED:
                    color = YELLOW;
                    System.out.println(color);
                    break;
                case YELLOW:
                    color = GREEN;
                    System.out.println(color);
                    break;
            }
        }
        System.out.println();
        Stream.of(Signal.class.getEnumConstants()).forEach(System.out::println);
    }
}
