package com.jayus.jvm.test;

public class B extends A{

    static {
        System.out.println("a");
    }

    public B() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        A a = new B();
        a = new B();
    }
}
