package com.jayus.onjava.nineteen.initable;

/**
 * @author : h zk
 * @date : 2022/8/1 18:06
 * @description :
 **/
public class Initable1 {
    static int STATIC_FINAL = 147;
    static {
        System.out.println("Initable1 init");
    }
}
