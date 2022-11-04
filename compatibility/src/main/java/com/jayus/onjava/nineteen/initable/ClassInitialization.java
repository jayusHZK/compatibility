package com.jayus.onjava.nineteen.initable;

import java.util.Random;

/**
 * @author : h zk
 * @date : 2022/8/1 17:45
 * @description :
 **/
public class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String[] args) {
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        System.out.println(Initable.STATIC_FINAL);
        System.out.println(Initable.STATIC_FINAL2);

        System.out.println(Initable1.STATIC_FINAL);
        try {
            Class<?> initable2 = Class.forName("com.jayus.onjava.nineteen.initable.Initable2");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable2.STATIC_FINAL);
    }
}
