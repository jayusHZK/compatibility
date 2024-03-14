package com.jayus.onjava.function.functional;

/**
 * @author : h zk
 * @date : 2022/7/19 11:39
 * @description :
 **/
public class FunctionalAnnotation {
    public String goodbye(String arg) {
        return "Goodbye," + arg;
    }

    public String hello(String arg) {
        return "Hello," + arg;
    }

    public static void main(String[] args) {
        FunctionalAnnotation fa = new FunctionalAnnotation();
        Functional f = fa::hello;
        System.out.println(f.goodbye("b"));
        FunctionalNoAnn fna = fa::goodbye;
        Functional f1 = a -> "Goodbyes," + a;
        FunctionalNoAnn fnal = a -> "Goodbye," + a;
        System.out.println(f1.goodbye("a"));
    }
}
